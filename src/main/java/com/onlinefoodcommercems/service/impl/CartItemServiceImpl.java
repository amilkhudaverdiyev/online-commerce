package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.Messages;
import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.entity.CartItem;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.CartItemMapper;
import com.onlinefoodcommercems.repository.CartItemRepository;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.CartItemService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Set;

@Service
@RequiredArgsConstructor
public class CartItemServiceImpl implements CartItemService {
    private final CartItemRepository cartRepository;
    private final CartItemMapper cartItemMapper;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @Override
    public CartItemResponse save(int quantity, Long id, Long userId) {
        var availableQuantity = 0;
        var customer = customerRepository.findById(userId)
                .orElseThrow(() -> new NotDataFound(Messages.CUSTOMER_NOT_FOUND));
        var product = productRepository.findProductStatusActivity(id)
                .orElseThrow(() -> new NotDataFound(Messages.PRODUCT_NOT_FOUND));
        double unitPrice = product.getUnitPrice();
        var cartItem = CartItem.builder()
                .customer(customer)
                .product(product)
                .quantity(quantity)
                .price(unitPrice)
                .totalPrice(unitPrice * quantity)
                .status(Status.ACTIVE)
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

    }

    @Override
    public List<CartItemResponse> findAll() {
        var addressAll = cartRepository.findAll();
        return cartItemMapper.toDTOs(addressAll);
    }

    @Override
    public void deleteCart(Long id) {
        cartRepository.deleteById(id);

    }
   public void deleteById(Long id) {
        var cart = cartRepository.getById(id);
        cart.setStatus(Status.DEACTIVE);
        cartRepository.save(cart);
    }

   public void enableById(Long id) {
        var cart = cartRepository.getById(id);
        cart.setStatus(Status.ACTIVE);
        cartRepository.save(cart);
    }
}



