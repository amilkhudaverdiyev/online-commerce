package com.onlinefoodcommercems.schedule;

import com.onlinefoodcommercems.dto.request.AccountRequest;
import com.onlinefoodcommercems.entity.Account;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.entity.Discount;
import com.onlinefoodcommercems.enums.DiscountStatus;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.property.AdminCardProperty;
import com.onlinefoodcommercems.repository.DiscountRepository;
import com.onlinefoodcommercems.service.AccountService;
import com.onlinefoodcommercems.service.CustomerService;
import com.onlinefoodcommercems.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateSchedule {

    private final DiscountRepository discountRepository;
    private final AdminCardProperty adminCardProperty;
    private final DiscountService discountService;
    private final CustomerService customerService;
    private final AccountService accountService;

    @SchedulerLock(name = "updateDiscountLoadingStatus")
    @Scheduled(cron = "${spring.quartz.scheduler-name.cron}")
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDiscountLoadingStatus() {
        System.out.println("active");
        var discount = discountRepository.findByStatus(DiscountStatus.LOADING);
        for (Discount discounts : discount) {
            discountService.activatedDiscount(discounts);
        }
        var activeDiscount = discountRepository.findByStatus(DiscountStatus.ACTIVE);
        for (Discount discounts : activeDiscount) {
            var unitPrice = discounts.getProduct().getUnitPrice();
            var costPrice = unitPrice - unitPrice * discounts.getDiscount() / 100;
            discounts.setDiscountPrice(costPrice);
            discountRepository.save(discounts);
        }
        System.out.println("schedule END");
    }

    @SchedulerLock(name = "updateDiscountActiveStatus")
    @Scheduled(fixedDelay = 10000)
    public void updateDiscountActiveStatus() {
        System.out.println("schedule start");
        var discount = discountRepository.findByStatus(DiscountStatus.ACTIVE);
        for (Discount discounts : discount) {
            discountService.terminatedDiscount(discounts);
        }
        var deactiveDiscount = discountRepository.findByStatus(DiscountStatus.DEACTIVE);
        for (Discount discounts : deactiveDiscount) {
            discounts.setDiscountPrice(0.0);
            discountRepository.save(discounts);
        }
        System.out.println("schedule END");
    }

    @SchedulerLock(name = "checkTimes")
    //@Scheduled(cron = "* 58 15 * * *")
    @Scheduled(fixedDelay = 50000)
    public void checkCustomerAdminStatus(){

        System.out.println("schedule start");
        var customers=  customerService.getCustomerByEnabledAdmin();
        for (Customer customer:customers ){
            customerService.updateCustomerStatus(customer);
            var account= AccountRequest
                    .builder()
                    .accountId(UUID.randomUUID().toString())
                    .name(customer.getName())
                    .id(customer.getId())
                    .cardNumber(adminCardProperty.getCardNumber().toString())
                    .expiryDate(adminCardProperty.getExpiryDate())
                    .amount(new BigDecimal(0))
                    .status(Status.ACTIVE)
                    .build();
            log.error("account {}" + account);
            accountService.createAccount(account);
        }
        System.out.println("schedule END");
    }
    @SchedulerLock(name = "checkTime")
    //@Scheduled(cron = "* 58 15 * * *")
    @Scheduled(fixedDelay = 50000)
    public void checkCustomerUserStatus(){

        System.out.println("schedule start");
        var customers=  customerService.getCustomerByEnabledUser();
        for (Customer customer:customers ){
            customerService.updateCustomerStatus(customer);
            var account= AccountRequest
                    .builder()
                    .accountId(UUID.randomUUID().toString())
                    .name(customer.getName())
                    .id(customer.getId())
                    .amount(new BigDecimal(0))
                    .status(Status.DEACTIVE)
                    .build();
            log.error("user {}" + account);
            accountService.createAccount(account);
        }

        System.out.println("schedule END");
    }
}