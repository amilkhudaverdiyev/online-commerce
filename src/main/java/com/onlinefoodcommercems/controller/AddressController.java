package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.Messages;
import com.onlinefoodcommercems.dto.request.AddressRequest;
import com.onlinefoodcommercems.dto.response.AddressResponse;
import com.onlinefoodcommercems.service.AddressService;
import com.onlinefoodcommercems.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/address")
@RequiredArgsConstructor
public class AddressController {
    private final AddressService addressService;

    @GetMapping("/all")
    public List<AddressResponse> getAllAddress() {
        return addressService.findAll();
    }

    @GetMapping("/all-active")
    public List<AddressResponse> getAllAddressActivated() {
        return addressService.findAllByActivated();
    }

    @PostMapping
    public ResponseEntity<String> createAddress(@RequestBody AddressRequest request) {
        addressService.save(request);
        return MessageUtils.getResponseEntity(Messages.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }
    @PutMapping("/{id}")
    public ResponseEntity<String> updateAddress(@PathVariable Long id,
                                                 @RequestBody AddressRequest request) {
        addressService.update(id, request);
        return MessageUtils.getResponseEntity(Messages.UPDATE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/delete/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> deletedAddress(@PathVariable Long id) {
        addressService.deleteById(id);
        return MessageUtils.getResponseEntity(Messages.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/enable/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> enabledAddress(@PathVariable Long id) {
        addressService.enableById(id);
        return MessageUtils.getResponseEntity(Messages.ENABLED_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public AddressResponse findById(@PathVariable Long id) {
        return addressService.findById(id);
    }

}
