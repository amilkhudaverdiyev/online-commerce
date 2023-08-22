package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.response.OrderResponse;
import com.onlinefoodcommercems.entity.CartItem;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.entity.Order;
import com.onlinefoodcommercems.entity.OrderDetail;
import com.onlinefoodcommercems.enums.OrderStatus;
import com.onlinefoodcommercems.enums.Roles;
import com.onlinefoodcommercems.enums.Status;
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
import lombok.extern.slf4j.Slf4j;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.text.DecimalFormat;
import java.time.LocalDateTime;
import java.util.List;
import java.util.Random;

@Service
@RequiredArgsConstructor
@Slf4j
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
        log.error("order {}", order);
        return orderMapper.toDTOs(order);
    }
    @Override
    public List<Order> findByStatus() {
        var order = orderRepository.findOrdersByStatus(OrderStatus.SENDING);
        log.error("order {}",order);
        return order;
    }
    @Override
    public void updateOrderStatus(Order order) {
        if(order.getDeliveryDate().isBefore(LocalDateTime.now())) {
            log.error("order {}", order);
            order.setStatus(OrderStatus.COMPLETED);
            orderRepository.save(order);
        }
    }


    @Override
    public List<OrderResponse> findAllOrdersByCustomer(String username) {
        var customer = customerRepository.findByUsername(username).orElseThrow(() -> new UsernameNotFoundException(ResponseMessage.USER_NOT_FOUND));
        log.error("orders {}",customer);
        var orders = customer.getOrders();
        log.error("orders {}",orders);
        return orderMapper.toDTOs(orders);
    }

    @Override
    public void deleteById(Long id) {
        var order = orderRepository.findById(id).orElseThrow(() -> new NotDataFound(ResponseMessage.ORDER_NOT_FOUND));
        log.error("order {}",order);
        order.setStatus(OrderStatus.CANCELED);
        orderRepository.save(order);
    }

    @Override
    public void save(Long id,LocalDateTime deliveryDate) throws MessagingException {
        var orderDetailId=  new DecimalFormat("000000")
                .format(new Random().nextLong(999999));
        var customer = customerRepository.findByIdAndAuthority(id).orElseThrow(() -> new UsernameNotFoundException(ResponseMessage.USER_NOT_FOUND));
        log.error("customer {}",customer);
        var carts = customer.getCartItems();
        log.error("carts {}",carts);
        var order = Order.builder()
                .deliveryDate(deliveryDate)
                .totalAmount(BigDecimal.valueOf(totalPrice(carts)))
                .customer(customer)
                .status(OrderStatus.LOADING)
                .build();
        orderRepository.save(order);
        log.error("order {}",order);
        var orderEntity = orderDetailMapper.cartToOrderDetail(carts);
        log.error("orderEntity {}",orderEntity);
        for (OrderDetail orderDetail : orderEntity
        ) {
            orderDetail.setId(Long.valueOf(orderDetailId));
            orderDetail.setOrder(order);
            availableQuantity=orderDetail.getProduct().getCurrentQuantity()-orderDetail.getQuantity();
            orderDetail.getProduct().setCurrentQuantity(availableQuantity);
            if(orderDetail.getProduct().getCurrentQuantity()==0) {
                orderDetail.getProduct().setStatus(Status.DEACTIVE);
            }
            orderDetailRepository.save(orderDetail);
            cartItemRepository.deleteCartItemByCustomer(id);
            log.error("order detail {}",orderDetail);
        }
        emailSender.sendMailToAdmin(customer.getUsername());
    }
    private double totalPrice(List<CartItem> cartItemsList) {
        double totalPrice = 0.0;
        log.error("cart item {}",cartItemsList);
        for (CartItem item : cartItemsList) {
            totalPrice += item.getPrice() * item.getQuantity();
        }
        log.error("total price " + totalPrice);
        return totalPrice;
    }
}
