package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.response.OrderResponse;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.service.OrderService;
import com.onlinefoodcommercems.service.email.EmailSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.time.LocalDateTime;
import java.util.List;

@RestController
@RequestMapping("/order")
@RequiredArgsConstructor
public class OrderController {
    private final OrderService orderService;
    private final EmailSender emailSender;

    @GetMapping ("/place-order/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseDetail placeOrder(@PathVariable Long id,
                                     @RequestParam LocalDateTime deliveryDate) throws MessagingException {
        orderService.save(id,deliveryDate);
        return ResponseDetail.builder()
                .message(ResponseMessage.ADD_SUCCESSFULLY)
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value())
                .build();
    }

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<OrderResponse> getAllOrders() {
        return orderService.findAll();
    }

    @GetMapping("/status")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<OrderResponse> getAllOrdersByStatus(@RequestParam String status) {
        return orderService.findOrderByStatus(status);
    }

    @GetMapping("/customer-orders")
 @PreAuthorize("#customer.getUsername()==authentication.principal.username")
    public List<OrderResponse> getAllOrdersByCustomer(@AuthenticationPrincipal Customer customer) {
        return orderService.findAllOrdersByCustomer(customer.getUsername());
    }

    @PostMapping(path = "/send")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail sendMailToUser(@RequestParam String username,
                               @RequestParam("attachment") MultipartFile attachment) throws MessagingException, IOException {
        byte[] bytes = attachment.getBytes();
        Path path = Paths.get(ResponseMessage.UPLOADER_FOLDER + attachment.getOriginalFilename());
        Files.write(path, bytes);
        emailSender.sendMailToUser(username,
                attachment);
        return ResponseDetail.builder()
                .message(ResponseMessage.MESSAGE_SEND_SUCCESFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @DeleteMapping("/deleted-order/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail DeletedOrder(@PathVariable Long id) {
        orderService.deleteById(id);
        return ResponseDetail.builder()
                .message(ResponseMessage.DELETE_SUCCESSFULLY)
                .status(HttpStatus.NO_CONTENT.getReasonPhrase())
                .statusCode(HttpStatus.NO_CONTENT.value())
                .build();
    }

    @PostMapping(path = "/send-email")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseDetail sendMailToCancelOrder(@AuthenticationPrincipal Customer customer) throws MessagingException, IOException {
        emailSender.sendMailToCancelOrder(customer.getUsername());
        return ResponseDetail.builder()
                .message(ResponseMessage.MESSAGE_SEND_SUCCESFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();

    }
}
