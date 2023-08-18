package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.CartDetails;
import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.service.CartItemService;
import com.onlinefoodcommercems.utils.LinkUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
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
    public ResponseDetail updateCart(@AuthenticationPrincipal Customer customer,
                                             @RequestBody CartItemRequest request) {
        cartItemService.update(customer.getId(), request);
        return ResponseDetail.builder()
                .message(ResponseMessage.UPDATE_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseDetail deleteItem(@PathVariable Long id
    ) {
        cartItemService.deleteCart(id);
        return ResponseDetail.builder()
                .message(ResponseMessage.DELETE_SUCCESSFULLY)
                .status(HttpStatus.NO_CONTENT.getReasonPhrase())
                .statusCode(HttpStatus.NO_CONTENT.value())
                .build();
    }

    @PostMapping("/add-to-cart")
    @PreAuthorize("hasAuthority('USER')")
    public CartDetails create(@RequestParam("id") Long id,
                              @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                              @AuthenticationPrincipal Customer customer) {
        try {
            cartItemService.save(quantity, id, customer.getUsername());
            return CartDetails.builder()
                    .message(ResponseMessage.ADD_SUCCESSFULLY)
                    .status(HttpStatus.CREATED.getReasonPhrase())
                    .statusCode(HttpStatus.CREATED.value())
                    .link(LinkUtils.createPlaceOrderLink(customer.getId()))
                    .build();
        } catch (NotDataFound e) {
            return CartDetails.builder()
                    .message(ResponseMessage.PRODUCT_NOT_FOUND)
                    .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .statusCode(HttpStatus.NOT_FOUND.value()).build();
        } catch (Exception e) {
            return CartDetails.builder()
                    .message(ResponseMessage.ERROR)
                    .status(HttpStatus.BAD_REQUEST.getReasonPhrase())
                    .statusCode(HttpStatus.BAD_REQUEST.value()).build();
        }
    }
}