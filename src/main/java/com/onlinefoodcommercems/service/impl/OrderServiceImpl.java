package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.response.OrderResponse;
import com.onlinefoodcommercems.entity.CartItem;
import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.entity.OrderDetail;
import com.onlinefoodcommercems.enums.OrderStatus;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.OrderDetailMapper;
import com.onlinefoodcommercems.mapper.OrderMapper;
import com.onlinefoodcommercems.repository.CartItemRepository;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.repository.OrderDetailRepository;
import com.onlinefoodcommercems.repository.OrderRepository;
import com.onlinefoodcommercems.service.OrderService;
import com.onlinefoodcommercems.service.email.EmailSender;
import jakarta.mail.MessagingException;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements OrderService {
    private Integer availableQuantity;
    private final OrderDetailMapper orderDetailMapper;
    private final EmailSender emailSender;
    private final OrderMapper orderMapper;
    private final CustomerRepository customerRepository;
    private final CartItemRepository cartItemRepository;
    private final OrderDetailRepository orderDetailRepository;
    private final OrderRepository orderRepository;

    @Override
    public List<OrderResponse> findAll() {
        var order = orderRepository.findAll();
        return orderMapper.toDTOs(order);
    }
    @Override
    public List<OrderResponse> findOrderByStatus(String status) {
        var order = orderRepository.findOrderByStatus(status);
        return orderMapper.toDTOs(order);
    }

    @Override
    public List<OrderResponse> findAllOrdersByCustomer(String username) {
        var customer = customerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(ResponseMessage.USER_NOT_FOUND));
        var orders = customer.getOrders();
        return orderMapper.toDTOs(orders);
    }

    @Override
    public void deleteById(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new NotDataFound(ResponseMessage.ORDER_NOT_FOUND));
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }

    @Override
    public void save(Long id) throws MessagingException {
        var orderDetailId=  new DecimalFormat("000000")
                .format(new Random().nextLong(999999));
        var customer = customerRepository.findById(id).orElseThrow(() -> new UsernameNotFoundException(ResponseMessage.USER_NOT_FOUND));
        var carts = customer.getCartItems();
        var order = Order.builder()
                .deliveryDate(LocalDateTime.now().plusMinutes(15))
                .totalAmount(totalPrice(carts))
                .customer(customer)
                .status(OrderStatus.LOADING)
                .build();
        orderRepository.save(order);
        var orderEntity = orderDetailMapper.cartToOrderDetail(carts);
        for (OrderDetail orderDetail : orderEntity
        ) {
            orderDetail.setId(Long.valueOf(orderDetailId));
            orderDetail.setOrder(order);
            availableQuantity=orderDetail.getProduct().getCurrentQuantity()-orderDetail.getQuantity();
            orderDetail.getProduct().setCurrentQuantity(availableQuantity);
            orderDetailRepository.save(orderDetail);
            cartItemRepository.deleteCartItemByCustomer(id);
        }
        emailSender.sendMailToAdmin(customer.getUsername());
    }
    private double totalPrice(List<CartItem> cartItemsList) {
        double totalPrice = 0.0;
        for (CartItem item : cartItemsList) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        return totalPrice;
    }
}
