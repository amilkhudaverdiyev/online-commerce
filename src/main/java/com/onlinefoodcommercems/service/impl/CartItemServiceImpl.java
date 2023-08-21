package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.entity.CartItem;
import com.onlinefoodcommercems.entity.Discount;
import com.onlinefoodcommercems.enums.DiscountStatus;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.CartItemMapper;
import com.onlinefoodcommercems.mapper.CustomerMapper;
import com.onlinefoodcommercems.repository.CartItemRepository;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.CartItemService;
import com.onlinefoodcommercems.service.CustomerService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartRepository;
    private final CartItemMapper cartItemMapper;
    private final CustomerRepository customerRepository;
    private final CustomerService customerService;
    private final CustomerMapper customerMapper;
    private final ProductRepository productRepository;

    @Override
    public CartItemResponse save(int quantity, Long id, String username) {
        var customer = customerMapper.customerResponseToCustomer(customerService.findByUsername(username));
        log.error("customer {}" + customer);
        var product = productRepository.findProductStatusActivity(id)
                .orElseThrow(() -> new NotDataFound(ResponseMessage.PRODUCT_NOT_FOUND));
        log.error("product {}" + product);
        if (product.getCurrentQuantity() == 0) {
            product.setStatus(Status.DEACTIVE);
            productRepository.save(product);
            throw new NotDataFound(ResponseMessage.PRODUCT_NOT_FOUND);
        } else {
            List<Discount> discounts = product.getDiscount();
            log.error("discounts {}" + discounts);
            for (Discount discount : discounts
            ) {
                if (discount.getStatus() == DiscountStatus.ACTIVE) {
                    double unitPrice = discount.getDiscountPrice();
                    var cartItem = CartItem.builder()
                            .customer(customer)
                            .product(product)
                            .quantity(quantity)
                            .price(unitPrice)
                            .totalPrice(quantity * unitPrice)
                            .build();
                    if (product.getCurrentQuantity() < quantity) {
                        cartItem.setTotalPrice(unitPrice * product.getCurrentQuantity());
                        cartItem.setQuantity(product.getCurrentQuantity());
                    } else {
                        cartItem.setTotalPrice(quantity * unitPrice);
                    }
                    log.error("discount {}" + discount);
                    return cartItemMapper.toDTO(cartRepository.save(cartItem));
                }
            }
            double unitPrice = product.getUnitPrice();
            var cartItem = CartItem.builder()
                    .customer(customer)
                    .product(product)
                    .quantity(quantity)
                    .price(unitPrice)
                    .totalPrice(quantity * product.getUnitPrice())
                    .build();
            if (product.getCurrentQuantity() < quantity) {
                cartItem.setTotalPrice(product.getUnitPrice() * product.getCurrentQuantity());
                cartItem.setQuantity(product.getCurrentQuantity());
            } else {
                cartItem.setTotalPrice(quantity * product.getUnitPrice());
            }
            log.error("cart item {}" + cartItem);
            return cartItemMapper.toDTO(cartRepository.save(cartItem));
        }
    }


    @Override
    public void update(Long id, CartItemRequest cartItemRequest) {
        var cart = cartRepository.findByIdAndActivated(cartItemRequest.getId(), id).orElseThrow();
        log.error("cart {}" + cart);
        cart.setQuantity(cartItemRequest.getQuantity());
        cart.setTotalPrice(cart.getQuantity() * cart.getPrice());
        log.error("cart {}" + cart);
        cartRepository.save(cart);

    }

    @Override
    public List<CartItemResponse> findAll() {
        var carts = cartRepository.findAll();
        log.error("carts " +carts);
        return cartItemMapper.toDTOs(carts);
    }

    @Override
    public List<CartItemResponse> getCart(String username) {
        var customer = customerRepository.findByUsername(username)
                 .orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
        log.error("customer " +customer);
        var carts = customer.getCartItems();
        log.error("carts " +carts);
        return cartItemMapper.toDTOs(carts);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

}




