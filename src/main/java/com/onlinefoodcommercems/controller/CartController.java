package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.CartDetails;
import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.service.CartItemService;
import com.onlinefoodcommercems.utils.LinkUtils;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
@Validated
public class CartController {
    private final CartItemService cartItemService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CartItemResponse> getAllCart() {
        return cartItemService.findAll();
    }

    @GetMapping("/user")
    @PreAuthorize("hasAuthority('USER')")
    public List<CartItemResponse> getAllCarts(Principal principal) {
        return cartItemService.getCart(principal.getName());
    }

    @PutMapping
    @PreAuthorize("hasAuthority('USER')")
    public ResponseDetail updateCart(@RequestBody @Valid CartItemRequest request,
                                     Principal principal
    ) {
        cartItemService.update(request, principal.getName());
        return ResponseDetail.builder()
                .message(ResponseMessage.UPDATE_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @DeleteMapping("/delete/{id}")
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
                              Principal principal) {
        try {
            cartItemService.save(quantity, id, principal.getName());
            return CartDetails.builder()
                    .message(ResponseMessage.ADD_SUCCESSFULLY)
                    .status(HttpStatus.CREATED.getReasonPhrase())
                    .statusCode(HttpStatus.CREATED.value())
                    .link(LinkUtils.createPlaceOrderLink(principal.getName()))
                    .build();
        } catch (NotDataFound e) {
            return CartDetails.builder()
                    .message(ResponseMessage.PRODUCT_NOT_FOUND)
                    .status(HttpStatus.NOT_FOUND.getReasonPhrase())
                    .statusCode(HttpStatus.NOT_FOUND.value()).build();
        }
    }
}