package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;

import java.util.List;

public interface CartItemService {
    CartItemResponse save(int quantity,Long id,Long userId);

    void update(Long id, CartItemRequest cartItemRequest);
     List<CartItemResponse> findAll();
     List<CartItemResponse> getCart(Long id);

     void deleteCart(Long id);
}
