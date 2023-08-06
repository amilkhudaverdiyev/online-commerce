package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.CartDetails;
import com.onlinefoodcommercems.dto.request.CartItemRequest;
import com.onlinefoodcommercems.dto.response.CartItemResponse;
import com.onlinefoodcommercems.entity.Customer;
import com.onlinefoodcommercems.repository.CustomerRepository;
import com.onlinefoodcommercems.service.CartItemService;
import com.onlinefoodcommercems.service.CustomerService;
import com.onlinefoodcommercems.utils.LinkUtils;
import com.onlinefoodcommercems.utils.MessageUtils;
import jakarta.servlet.http.HttpServlet;
import jakarta.servlet.http.HttpServletRequest;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/cart")
@RequiredArgsConstructor
public class CartController {
    private final CartItemService cartItemService;
    private final CustomerService customerService;
    private final CustomerRepository customerRepository;

    @GetMapping("/all")
    public List<CartItemResponse> getAllCart() {
        return cartItemService.findAll();
    }

    @GetMapping("/alls")
    @PreAuthorize("hasAuthority('USER')")
    public List<CartItemResponse> getAllCarts(Principal principal) {
        var customer=customerService.findByUsername(principal.getName());
        return cartItemService.getCart(customer.getUsername());

    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> updateCart(@PathVariable Long id,
                                             @RequestBody CartItemRequest request) {
        cartItemService.update(id, request);
        return MessageUtils.getResponseEntity(ResponseMessage.UPDATE_SUCCESSFULLY, HttpStatus.OK);
    }

    @DeleteMapping("/delete")
    @PreAuthorize("hasAuthority('USER')")
    public ResponseEntity<String> deleteItem(@RequestParam Long id
    ) {
        cartItemService.deleteCart(id);
        return MessageUtils.getResponseEntity(ResponseMessage.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @PostMapping("/add-to-cart")
    @PreAuthorize("hasAuthority('USER')")
    public CartDetails create(@RequestParam("id") Long id,
                         @RequestParam(value = "quantity", required = false, defaultValue = "1") int quantity,
                         @AuthenticationPrincipal Customer userDetails) {
        try {
            cartItemService.save(quantity, id, userDetails.getUsername());
            return CartDetails.builder()
                    .message(MessageUtils.getResponseEntity(ResponseMessage.ADD_SUCCESSFULLY, HttpStatus.CREATED))
                    .link(LinkUtils.createPlaceOrderLink(userDetails.getId()))
                    .build();
        } catch (Exception e) {
            return CartDetails.builder()
                    .message(MessageUtils.getResponseEntity(ResponseMessage.SOMETHING_WENT_WRONG, HttpStatus.BAD_REQUEST)).build();
        }
    }
}