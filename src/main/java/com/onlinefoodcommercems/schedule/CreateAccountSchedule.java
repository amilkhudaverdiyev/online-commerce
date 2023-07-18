package com.onlinefoodcommercems.schedule;

import com.onlinefoodcommercems.entity.Category;
import com.onlinefoodcommercems.entity.Discount;
import com.onlinefoodcommercems.entity.Product;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.repository.CategoryRepository;
import com.onlinefoodcommercems.repository.DiscountRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.CategoryService;
import com.onlinefoodcommercems.service.DiscountService;
import com.onlinefoodcommercems.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import net.javacrumbs.shedlock.spring.annotation.SchedulerLock;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Random;

@Component
@RequiredArgsConstructor
@Slf4j
public class CreateAccountSchedule {


private final DiscountRepository discountRepository;
private final DiscountService discountService;


    @SchedulerLock(name = "checkTime")
    @Scheduled(cron = "* * * * * *")
    @Transactional(propagation = Propagation.REQUIRED)
    public void DiscountActiveStatus(){
        System.out.println("active");
        var discount= discountService.getCustomerByStatus();
        for ( Discount discounts:discount ){
            discountService.activatedDiscount(discounts);

        }



        System.out.println("schedule END");
    }
//    @SchedulerLock(name = "checkTime")
//    @Scheduled(cron = "* * * * * *")
//    @Transactional(propagation = Propagation.REQUIRED)
//    public void updateProductUnitPrice(){
//        System.out.println("update");
//        var discounts= discountRepository.findByStatus();
//
//        for ( Discount discount:discounts ){
//            discountService.updateProductUnitPrice(discount);
//
//        }
//        System.out.println("schedule END");
//    }
    //@Scheduled(cron = "* 58 15 * * *")
    @SchedulerLock(name = "checkTime")
    @Scheduled(fixedDelay = 10000)
    public void DiscountDeactiveStatus(){
        System.out.println("schedule start");
      var discount= discountRepository.findByStatus();

        for ( Discount discounts:discount ){
            discountService.terminatedDiscount(discounts);
        }



        System.out.println("schedule END");
    }


}
