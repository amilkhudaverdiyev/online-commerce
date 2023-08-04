package com.onlinefoodcommercems.service.email;

import jakarta.mail.MessagingException;
import org.springframework.web.multipart.MultipartFile;

public interface EmailSender {

    void sendMailToUser(String username,
                        MultipartFile attachment) throws MessagingException;

    void sendMailToAdmin(String username) throws MessagingException;

    void sendMailToCancelOrder(String username) throws MessagingException;
     void send(String to, String email);
}
