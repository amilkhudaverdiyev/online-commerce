package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.entity.CartItem;

import java.util.List;
import java.util.Map;

public interface CartItemService {
    CartItemResponse save(int quantity,Long id,String userId);

    void update(Long id, CartItemRequest cartItemRequest);
     List<CartItemResponse> findAll();
     List<CartItemResponse> getCart(String username);
     void deleteCart(Long id);

}
