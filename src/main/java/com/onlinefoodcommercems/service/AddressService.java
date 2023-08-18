package com.onlinefoodcommercems.service;

import com.onlinefoodcommercems.dto.request.AddressRequest;
import com.onlinefoodcommercems.dto.response.AddressResponse;
import com.onlinefoodcommercems.enums.Status;

import java.util.List;

public interface AddressService {
    AddressResponse save(AddressRequest request);

    void update(Long id, AddressRequest addressRequest);

    List<AddressResponse> findAllByActivated(Status status);

    List<AddressResponse> findAll();
    AddressResponse findById(Long id);
    void deleteById(Long id);

    void enableById(Long id);

}
