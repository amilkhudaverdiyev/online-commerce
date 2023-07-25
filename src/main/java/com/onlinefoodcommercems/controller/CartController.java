package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.Messages;
import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.dto.response.CustomerResponse;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.repository.CartItemRepository;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.CartItemService;
import com.onlinefoodcommercems.service.ProductService;
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
    @PostMapping
    public ResponseEntity<String> createCart(@RequestParam("id") Long id,
                                                 @RequestParam(value = "quantity",required = false, defaultValue = "1") int quantity,
                                                 @RequestParam Long userId) {
        cartItemService.save(quantity,id,userId);
        return MessageUtils.getResponseEntity(Messages.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }
    @DeleteMapping("/delete")
    public void deleteItem(@RequestParam Long id
    ) {

            cartItemService.deleteCart(id);
    }
    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> deletedAddress(@PathVariable Long id) {
        cartItemService.deleteById(id);
        return MessageUtils.getResponseEntity(Messages.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/enable/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> enabledAddress(@PathVariable Long id) {
        cartItemService.enableById(id);
        return MessageUtils.getResponseEntity(Messages.ENABLED_SUCCESSFULLY, HttpStatus.OK);
    }
}
