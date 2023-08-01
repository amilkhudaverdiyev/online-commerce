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
import com.onlinefoodcommercems.repository.CartItemRepository;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.repository.DiscountRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartRepository;
    private final CartItemMapper cartItemMapper;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;
    private final DiscountRepository discountRepository;

    @Override
    public CartItemResponse save(int quantity, Long id, Long userId) {
        var availableQuantity = 0;
        var customer = customerRepository.findById(userId)
                .orElseThrow(() -> new NotDataFound(ResponseMessage.CUSTOMER_NOT_FOUND));
        var product = productRepository.findProductStatusActivity(id)
                .orElseThrow(() -> new NotDataFound(ResponseMessage.PRODUCT_NOT_FOUND));
        List<Discount> discounts = product.getDiscount();
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
                    availableQuantity = product.getCurrentQuantity() - quantity;
                }
                product.setCurrentQuantity(availableQuantity);
                if (product.getCurrentQuantity() == 0) {
                    product.setStatus(Status.DEACTIVE);
                }
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
            availableQuantity = product.getCurrentQuantity() - quantity;
        }
        product.setCurrentQuantity(availableQuantity);
        if (product.getCurrentQuantity() == 0) {
            product.setStatus(Status.DEACTIVE);
        }
        return cartItemMapper.toDTO(cartRepository.save(cartItem));
    }


    @Override
    public void update(Long id, CartItemRequest cartItemRequest) {
        var cart = cartRepository.findByIdAndActivated(cartItemRequest.getId(), id).orElseThrow();
        cart.setQuantity(cartItemRequest.getQuantity());
        cart.setTotalPrice(cart.getQuantity() * cart.getPrice());
        cartRepository.save(cart);

    }

    @Override
    public List<CartItemResponse> findAll() {
        var addressAll = cartRepository.findAll();
        return cartItemMapper.toDTOs(addressAll);
    }

    @Override
    public List<CartItemResponse> getCart(Long id) {
        var customer = customerRepository.findById(id).orElseThrow();
        var carts = customer.getCartItems();
        return cartItemMapper.toDTOs(carts);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);
    }

    }




