package com.onlinefoodcommercems.service.email;

import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.enums.OrderStatus;
import com.onlinefoodcommercems.property.MailProperty;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.repository.OrderRepository;
import com.onlinefoodcommercems.utils.LinkUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.text.DecimalFormat;
import java.util.Random;


@Service
@RequiredArgsConstructor

public class EmailSenderService implements EmailSender {
    private final JavaMailSender javaMailSender;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;
    private final MailProperty mailProperty;

    @Override
    public void sendMailToUser(String username, MultipartFile attachment) throws MessagingException {
        var customer = customerRepository.findByUsername(username).orElseThrow();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(mailProperty.getUsername());
        mimeMessageHelper.setText("<html> <body> <p><h1> Dear," + customer.getName() + " " + customer.getSurname() + ",</p><p>Your order has been accepted"
                + "</body></html>", true);
        mimeMessageHelper.setTo(customer.getUsername());
        mimeMessageHelper.addAttachment(attachment.getOriginalFilename(), attachment);
        javaMailSender.send(mimeMessage);
        var orders = orderRepository.findCustomerInOrder(customer.getId());
        for (Order order : orders
        ) {
            order.setStatus(OrderStatus.ACCEPTED);
            orderRepository.save(order);
        }
    }

    @Override
    public void sendMailToAdmin(String username) throws MessagingException {
        var customer = customerRepository.findByUsername(username).orElseThrow();
        var link = LinkUtils.downloadPDFLink(customer.getId());
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(customer.getUsername());
        mimeMessageHelper.setTo(mailProperty.getUsername());
        mimeMessageHelper.setText("<html> <body> <p><h1> Order came from a user named " + customer.getUsername()
                + "<br>" + link + "</body></html>", true);
        javaMailSender.send(mimeMessage);
    }

    @Override
    public void sendMailToCancelOrder(String username) throws MessagingException {
        var customer = customerRepository.findByUsername(username).orElseThrow();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom(customer.getUsername());
        mimeMessageHelper.setTo(mailProperty.getUsername());
        mimeMessageHelper.setText(customer.getUsername() + " wants to cancel the order"
                + " " + "</body></html>", true);
        javaMailSender.send(mimeMessage);
    }

    @Override
    @Async
    public void send(String to, String email) {
        try {
            MimeMessage mimeMessage = javaMailSender.createMimeMessage();
            MimeMessageHelper helper =
                    new MimeMessageHelper(mimeMessage, "utf-8");
            helper.setText(email, true);
            helper.setTo(to);
            helper.setSubject("Confirm your email");
            helper.setFrom(mailProperty.getUsername());
            javaMailSender.send(mimeMessage);
        } catch (MessagingException e) {
            throw new IllegalStateException("failed to send email");
        }
    }

    @Override
    public void sendOtpEmail(String email) throws MessagingException {
        var otp = new DecimalFormat("000000")
                .format(new Random().nextInt(999999));
        var customer = customerRepository.findByUsername(email).orElseThrow();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper helper =
                new MimeMessageHelper(mimeMessage, "utf-8");
        helper.setTo(email);
        helper.setSubject("Verify OTP");
        helper.setText(otp + "</body></html>", true);
        customer.setActivationCode(otp);
        customerRepository.save(customer);
        javaMailSender.send(mimeMessage);
    }

}

