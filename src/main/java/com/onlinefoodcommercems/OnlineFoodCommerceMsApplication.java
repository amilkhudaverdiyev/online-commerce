package com.onlinefoodcommercems;


import net.javacrumbs.shedlock.spring.annotation.EnableSchedulerLock;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.scheduling.annotation.EnableScheduling;

@SpringBootApplication
@EnableScheduling
@EnableSchedulerLock(defaultLockAtMostFor = "PT30S")
public class OnlineFoodCommerceMsApplication {



    public static void main(String[] args) {
        SpringApplication.run(OnlineFoodCommerceMsApplication.class, args);
    }

}

