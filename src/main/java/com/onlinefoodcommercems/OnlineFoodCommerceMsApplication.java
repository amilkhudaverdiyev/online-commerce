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
//@EventListener(ApplicationReadyEvent.class)
//    public void triggerEMail() throws MessagingException {
//        emailSender.sendMailWithAttachment("amilxudaverdiyev00@gmail.com",
//                "This is Body",
//                "This is Email with attachment",
//                "C:\\Users\\Cabir\\Downloads\\pdf_2023-07-30_07_08_25.pdf");
}

