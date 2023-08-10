package com.onlinefoodcommercems.schedule;

import com.onlinefoodcommercems.entity.Discount;
import com.onlinefoodcommercems.repository.DiscountRepository;
import com.onlinefoodcommercems.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateSchedule {

    private final DiscountRepository discountRepository;
    private final DiscountService discountService;

    @SchedulerLock(name = "updateDiscountLoadingStatus")
    @Scheduled(cron = "${spring.quartz.scheduler-name.cron}")
    @Transactional(propagation = Propagation.REQUIRED)
    public void updateDiscountLoadingStatus() {
        System.out.println("active");
        var discount = discountRepository.findByLoadingStatus();
        for (Discount discounts : discount) {
            discountService.activatedDiscount(discounts);
        }
        var activeDiscount = discountRepository.findByActiveStatus();
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
        var discount = discountRepository.findByActiveStatus();
        for (Discount discounts : discount) {
            discountService.terminatedDiscount(discounts);
        }
        var deactiveDiscount = discountRepository.findByDeactiveStatus();
        for (Discount discounts : deactiveDiscount) {
            discounts.setDiscountPrice(0.0);
            discountRepository.save(discounts);
        }
        System.out.println("schedule END");
    }
}