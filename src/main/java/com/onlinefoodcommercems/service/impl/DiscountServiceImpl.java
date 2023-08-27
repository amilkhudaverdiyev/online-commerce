package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.DiscountDto;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import com.onlinefoodcommercems.entity.Discount;
import com.onlinefoodcommercems.enums.DiscountStatus;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.DiscountMapper;
import com.onlinefoodcommercems.repository.DiscountRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.DiscountService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepository;
    private final ProductRepository productRepository;
    private final DiscountMapper discountMapper;


    @Override
    public DiscountResponse addDiscount(DiscountRequest discountRequest) {
        log.error("discountRequest {}", discountRequest);
        var discountEntity = discountMapper.fromDTO(discountRequest);
        var product = productRepository.findProductStatusActivity(discountRequest.getProduct().getId())
                .orElseThrow(() -> new NotDataFound(ResponseMessage.PRODUCT_NOT_FOUND));
        discountEntity.setProduct(product);
        log.error("discount {}", discountEntity);
        return discountMapper.toDTO(discountRepository.save(discountEntity));
    }

    @Override
    public void terminatedDiscount(Discount discount) {
        log.error("discount {}", discount);
        if (LocalDateTime.now().isAfter(discount.getEndDate())) {
            discount.setStatus(DiscountStatus.DEACTIVE);
        }
        log.error("discountSet {}", discount);
        discountRepository.save(discount);

    }

    @Override
    public void activatedDiscount(Discount discount) {
        log.error("discount {}", discount);
        if (LocalDateTime.now().isAfter(discount.getDiscountDate()) && LocalDateTime.now().isBefore(discount.getEndDate())) {
            discount.setStatus(DiscountStatus.ACTIVE);
        }
        log.error("discountSet {}", discount);
        discountRepository.save(discount);
    }

    @Override
    public List<DiscountDto> findAllByActivated(DiscountStatus status) {
        log.error("status {}", status);
        var activeDiscount = discountRepository.findByStatus(status);
        log.error("activeDiscount {}", activeDiscount);
        return discountMapper.toDTOList(activeDiscount);
    }
}





