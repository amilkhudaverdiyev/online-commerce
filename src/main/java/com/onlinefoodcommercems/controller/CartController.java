package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.CartDetails;
import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.service.CartItemService;
import com.onlinefoodcommercems.utils.LinkUtils;
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

    @GetMapping("/all")
    public List<CartItemResponse> getAllCart() {
        return cartItemService.findAll();
    }

    @GetMapping("/alls")
    public List<CartItemResponse> getAllCarts(@RequestParam Long id) {
        return cartItemService.getCart(id);

    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCart(@PathVariable Long id,
                                             @RequestBody CartItemRequest request) {
        cartItemService.update(id, request);
        return MessageUtils.getResponseEntity(ResponseMessage.UPDATE_SUCCESSFULLY, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    public ResponseEntity<String> deleteItem(@RequestParam Long id
    ) {
        cartItemService.deleteCart(id);
        return MessageUtils.getResponseEntity(ResponseMessage.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
    public CartDetails create(@RequestParam("id") Long id,
                              @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                              @RequestParam Long userId) {
        try {
            cartItemService.save(quantity, id, userId);
            return CartDetails.builder()
                    .message(MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.CREATED))
                    .link(LinkUtils.createPlaceOrderLink(userId))
                    .build();
        } catch (Exception e) {
            return CartDetails.builder()
                    .message(MessageUtils.getResponseEntity(ResponseMessage.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST)).build();
        }
    }
}