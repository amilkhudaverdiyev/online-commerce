package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.AddressRequest;
import com.onlinefoodcommercems.dto.response.AddressResponse;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.AddressMapper;
import com.onlinefoodcommercems.repository.AddressRepository;
import com.onlinefoodcommercems.service.AddressService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class AddressServiceImpl implements AddressService {
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;

    @Override
    public AddressResponse save(AddressRequest request) {
        log.error("request {}",request);
        var addressEntity = addressMapper.fromDTO(request);
        var createdAddress = addressRepository.save(addressEntity);
        log.error("createdAddress {}",createdAddress);
        return addressMapper.toDTO(createdAddress);
    }

    @Override
    public void update(Long id, AddressRequest addressRequest) {
        log.error("addressRequest {}",addressRequest);
    var address = addressRepository.findById(id).orElseThrow(() -> new NotDataFound(ResponseMessage.ADDRESS_NOT_FOUND));
        log.error("address {}",address);
    addressMapper.toDTOmap(address, addressRequest);
        addressRepository.save(address);

    }
    @Override
    public List<AddressResponse> findAllByActivated(Status status) {
        log.error("status {}",status);
        var activeStatus = addressRepository.findByStatus(status);
        log.error("activeStatus {}",activeStatus);
        return addressMapper.toDTOs(activeStatus);
    }

    @Override
    public List<AddressResponse> findAll() {
        var addressAll = addressRepository.findAll();
        log.error("addressAll {}",addressAll);
        return addressMapper.toDTOs(addressAll);
    }

    @Override
    public AddressResponse findById(Long id) {
        var address = addressRepository.findById(id).orElseThrow(()
                -> new NotDataFound(ResponseMessage.PRODUCT_NOT_FOUND));
        log.error("address {}",address);
        return addressMapper.toDTO(address);
    }

    @Override
    public void deleteById(Long id) {
        log.error("id {}",id);
        var address = addressRepository.getById(id);
        address.setStatus(Status.DEACTIVE);
        log.error("address {}",address);
        addressRepository.save(address);
    }

    @Override
    public void enableById(Long id) {
        log.error("id {}",id);
        var address = addressRepository.getById(id);
        address.setStatus(Status.ACTIVE);
        log.error("address {}",address);
        addressRepository.save(address);
    }
}
