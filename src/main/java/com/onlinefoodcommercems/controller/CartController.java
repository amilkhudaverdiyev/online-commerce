package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.CartItemService;
import com.onlinefoodcommercems.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartItemService cartItemService;
    private final CustomerRepository customerRepository;
    private final ProductRepository productRepository;

    @GetMapping("/all")
    public List<CartItemResponse> getAllCart() {
        return cartItemService.findAll();

    }
    @GetMapping("/alls")
    public List<CartItemResponse> getAllCarts(@RequestParam Long id)
    {
//        var customer = customerRepository.findById(id).orElseThrow();
//        var carts = customer.getCartItems();
        return cartItemService.getCart(id);

    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateCart(@PathVariable Long id,
                                                 @RequestBody CartItemRequest request) {
        cartItemService.update(id, request);
        return MessageUtils.getResponseEntity(ResponseMessage.UPDATE_SUCCESSFULLY, HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> createCart(@RequestParam("id") Long id,
                                             @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                                             @RequestParam Long userId) {
        cartItemService.save(quantity, id, userId);
        return MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteItem(@RequestParam Long id
    ) {
        cartItemService.deleteCart(id);
        return MessageUtils.getResponseEntity(ResponseMessage.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }
}
