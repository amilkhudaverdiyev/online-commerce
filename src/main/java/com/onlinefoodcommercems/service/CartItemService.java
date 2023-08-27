package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;

import java.util.List;

public interface CartItemService {
    CartItemResponse save(int quantity, Long id, String username);

    void update(CartItemRequest cartItemRequest, String username);

    List<CartItemResponse> findAll();

    List<CartItemResponse> getCart(String username);

    void deleteCart(Long id);

}
