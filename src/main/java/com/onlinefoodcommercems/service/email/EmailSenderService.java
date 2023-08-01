package com.onlinefoodcommercems.service.email;

import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.enums.OrderStatus;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.repository.OrderRepository;
import com.onlinefoodcommercems.utils.LinkUtils;
import jakarta.mail.MessagingException;
import jakarta.mail.internet.MimeMessage;
import lombok.RequiredArgsConstructor;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;


@Service
@RequiredArgsConstructor

public class EmailSenderService implements EmailSender {
    private final JavaMailSender javaMailSender;
    private final CustomerRepository customerRepository;
    private final OrderRepository orderRepository;

    @Override
    public void sendMailToUser(String username, MultipartFile attachment) throws MessagingException {
        var customer = customerRepository.findByUsername(username).orElseThrow();
        MimeMessage mimeMessage = javaMailSender.createMimeMessage();
        MimeMessageHelper mimeMessageHelper = new MimeMessageHelper(mimeMessage, true);
        mimeMessageHelper.setFrom("amil.xudaverdiyev30@gmail.com");
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
        mimeMessageHelper.setTo("amil.xudaverdiyev30@gmail.com");
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
        mimeMessageHelper.setTo("amil.xudaverdiyev30@gmail.com");
        mimeMessageHelper.setText(customer.getUsername() + " wants to cancel the order"
                + " " + "</body></html>", true);
        javaMailSender.send(mimeMessage);
    }


}

