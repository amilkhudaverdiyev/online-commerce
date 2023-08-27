package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.response.AddressResponse;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.dto.update.AddressUpdateRequest;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.security.Principal;
import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
@Validated
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AddressResponse> getAllAddress() {
        return addressService.findAll();
    }

    @GetMapping("/status/{status}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<AddressResponse> getAllAddressActivated(@PathVariable Status status) {
        return addressService.findAllByActivated(status);
    }

    @PutMapping()
    @PreAuthorize("hasAuthority('USER') or hasAuthority('ADMIN')")
    public ResponseDetail updateAddress(Principal principal,
                                        @RequestBody AddressUpdateRequest request) {
        addressService.update(principal.getName(), request);
        return ResponseDetail.builder()
                .message(ResponseMessage.UPDATE_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @DeleteMapping("/delete/{id}")
    public ResponseDetail deletedAddress(@PathVariable Long id) {
        addressService.deleteById(id);
        return ResponseDetail.builder()
                .message(ResponseMessage.DELETE_SUCCESSFULLY)
                .status(HttpStatus.NO_CONTENT.getReasonPhrase())
                .statusCode(HttpStatus.NO_CONTENT.value())
                .build();
    }

    @PutMapping(value = "/enable/{id}")
    public ResponseDetail enabledAddress(@PathVariable Long id) {
        addressService.enableById(id);
        return ResponseDetail.builder()
                .message(ResponseMessage.ENABLED_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value())
                .build();
    }

    @GetMapping(value = "/findById/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public AddressResponse findById(@PathVariable Long id) {
        return addressService.findById(id);
    }

}
