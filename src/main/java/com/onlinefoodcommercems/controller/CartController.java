package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.CartDetails;
import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.service.CartItemService;
import com.onlinefoodcommercems.service.CustomerService;
import com.onlinefoodcommercems.utils.LinkUtils;
import com.onlinefoodcommercems.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
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

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public List<CartItemResponse> getAllCarts(@AuthenticationPrincipal Customer customer) {
        return cartItemService.getCart(customer.getUsername());
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> updateCart(@AuthenticationPrincipal Customer customer,
                                             @RequestBody CartItemRequest request) {
        cartItemService.update(customer.getId(), request);
        return MessageUtils.getResponseEntity(ResponseMessage.UPDATE_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> deleteItem(@PathVariable Long id
    ) {
        cartItemService.deleteCart(id);
        return MessageUtils.getResponseEntity(ResponseMessage.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
    @PreAuthorize("hasAuthority('USER')")
    public CartDetails create(@RequestParam("id") Long id,
                              @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                              @AuthenticationPrincipal Customer customer) {
        try {
            cartItemService.save(quantity, id, customer.getUsername());
            return CartDetails.builder()
                    .message(MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.CREATED))
                    .link(LinkUtils.createPlaceOrderLink(customer.getId()))
                    .build();
        } catch (NotDataFound e) {
            return CartDetails.builder()
                    .message(MessageUtils.getResponseEntity(ResponseMessage.PRODUCT_NOT_FOUND, HttpStatus.NOT_FOUND)).build();
        } catch (Exception e) {
            return CartDetails.builder().message(MessageUtils.getResponseEntity(ResponseMessage.ERROR, HttpStatus.BAD_REQUEST)).build();
        }
    }
}