package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.Messages;
import com.onlinefoodcommercems.dto.request.AddressRequest;
import com.onlinefoodcommercems.dto.response.AddressResponse;
import com.onlinefoodcommercems.entity.Product;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.AddressMapper;
import com.onlinefoodcommercems.repository.AddressRepository;
import com.onlinefoodcommercems.service.AddressService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class AddressServiceImpl implements AddressService {
    private final AddressMapper addressMapper;
    private final AddressRepository addressRepository;
    @Override
    public AddressResponse save(AddressRequest request) {
        var addressEntity = addressMapper.fromDTO(request);
        var createdAddress = addressRepository.save(addressEntity);
        return addressMapper.toDTO(createdAddress);
    }

    @Override
    public void update(Long id, AddressRequest addressRequest) {
        var address=addressRepository.findById(id).orElseThrow(() -> new NotDataFound(Messages.ADDRESS_NOT_FOUND));
        addressMapper.toDTOmap(address,addressRequest);
        addressRepository.save(address);

    }

    @Override
    public List<AddressResponse> findAllByActivated() {
        var active = addressRepository.findAllByActivated();
        return addressMapper.toDTOs(active);
    }

    @Override
    public List<AddressResponse> findAll() {
        var addressAll = addressRepository.findAll();
        return addressMapper.toDTOs(addressAll);
    }

    @Override
    public AddressResponse findById(Long id) {
        var address = addressRepository.findById(id).orElseThrow(() -> new NotDataFound(Messages.ADDRESS_NOT_FOUND));
        return addressMapper.toDTO(address);
    }

    @Override
    public void deleteById(Long id) {
        var address = addressRepository.getById(id);
        address.setStatus(Status.DEACTIVE);
        addressRepository.save(address);
//        List<Product> deletedProducts = productRepository.findProductStatusInActiveByCategoryId(category.getId());
//        deletedProducts.forEach((product) ->
//                product.setStatus(Status.DEACTIVE));
//        productRepository.saveAll(deletedProducts);
    }

    @Override
    public void enableById(Long id) {
        var address = addressRepository.getById(id);
        address.setStatus(Status.ACTIVE);
        addressRepository.save(address);
//        List<Product> deletedProducts = productRepository.findProductStatusInDeactiveByCategoryId(category.getId());
//        deletedProducts.forEach((product) -> {
//            product.setStatus(Status.ACTIVE);
//        });
//        productRepository.saveAll(deletedProducts);
    }
}