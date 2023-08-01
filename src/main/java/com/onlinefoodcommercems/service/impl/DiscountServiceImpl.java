package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.DiscountDto;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import com.onlinefoodcommercems.entity.Discount;
import com.onlinefoodcommercems.enums.DiscountStatus;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.DiscountMapper;
import com.onlinefoodcommercems.repository.DiscountRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;
    private final DiscountMapper discountMapper;


    @Override
    public DiscountResponse addDiscount(DiscountRequest discountRequest) {
        var discountEntity = discountMapper.fromDTO(discountRequest);
        var product = productRepository.findProductStatusActivity(discountRequest.getProduct().getId())
                .orElseThrow(() -> new NotDataFound(ResponseMessage.PRODUCT_NOT_FOUND));
        discountEntity.setProduct(product);
        return discountMapper.toDTO(discountRepository.save(discountEntity));
    }
    @Override
    public void terminatedDiscount(Discount discount) {

        if (LocalDateTime.now().isAfter(discount.getEndDate())) {
            discount.setStatus(DiscountStatus.DEACTIVE);
        }
        discountRepository.save(discount);

    }

    @Override
    public void activatedDiscount(Discount discount) {
        if (LocalDateTime.now().isAfter(discount.getDiscountDate()) && LocalDateTime.now().isBefore(discount.getEndDate())) {
            discount.setStatus(DiscountStatus.ACTIVE);
        }
        discountRepository.save(discount);
    }

    @Override
    public List<DiscountDto> findAllByActivated() {
        var active = discountRepository.findAllByActivated();
        return discountMapper.toDTOList(active);
    }
}





