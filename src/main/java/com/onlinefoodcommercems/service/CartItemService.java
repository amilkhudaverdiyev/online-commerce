package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.response.AddressResponse;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.dto.response.CategoryResponse;

import java.util.List;

public interface CartItemService {
    CartItemResponse save(int quantity,Long id,Long userId);

    void update(Long id, CartItemRequest cartItemRequest);
     List<CartItemResponse> findAll();

     void deleteCart(Long id);
    void deleteById(Long id);

    void enableById(Long id);
}
