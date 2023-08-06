package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.response.OrderResponse;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.service.OrderService;
import com.onlinefoodcommercems.service.email.EmailSender;
import com.onlinefoodcommercems.utils.MessageUtils;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final EmailSender emailSender;

    @GetMapping("/place-order/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> placeOrder(@PathVariable Long id) throws MessagingException {
        orderService.save(id);
        return MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @GetMapping("/all")
    public List<OrderResponse> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/status")
    public List<OrderResponse> getAllOrdersByStatus(@PathVariable String status) {
        return orderService.findOrderByStatus(status);
    }

    @GetMapping("/all-customer")
 @PreAuthorize("#customer.getUsername()==authentication.principal.username")
    public List<OrderResponse> getAllOrdersByCustomer(@AuthenticationPrincipal Customer customer) {
        return orderService.findAllOrdersByCustomer(customer.getUsername());
    }

    @PostMapping(path = "/send")
    @PreAuthorize("hasAuthority('ADMIN')")
    public void sendMailToUser(@RequestParam String username,
                               @RequestParam("attachment") MultipartFile attachment) throws MessagingException, IOException {
        byte[] bytes = attachment.getBytes();
        Path path = Paths.get(ResponseMessage.UPLOADER_FOLDER + attachment.getOriginalFilename());
        Files.write(path, bytes);
        emailSender.sendMailToUser(username,
                attachment);
    }

    @RequestMapping(value = "/deleted-order/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseEntity<String> DeletedOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return MessageUtils.getResponseEntity(ResponseMessage.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @PostMapping(path = "/send-email")
    @PreAuthorize("hasAuthority('USER')")
    public void sendMailToCancelOrder(@AuthenticationPrincipal Customer customer) throws MessagingException, IOException {
        emailSender.sendMailToCancelOrder(customer.getUsername());

    }
}
