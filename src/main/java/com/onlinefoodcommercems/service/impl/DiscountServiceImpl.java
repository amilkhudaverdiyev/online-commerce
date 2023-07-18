package com.onlinefoodcommercems.service.impl;

import com.onlinefoodcommercems.constants.CakeHouseConstants;
import com.onlinefoodcommercems.dto.request.DiscountRequest;
import com.onlinefoodcommercems.dto.response.DiscountResponse;
import com.onlinefoodcommercems.entity.Discount;
import com.onlinefoodcommercems.enums.Status;
import com.onlinefoodcommercems.exception.NotDataFound;
import com.onlinefoodcommercems.mapper.DiscountMapper;
import com.onlinefoodcommercems.repository.DiscountRepository;
import com.onlinefoodcommercems.repository.ProductRepository;
import com.onlinefoodcommercems.service.DiscountService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DiscountServiceImpl implements DiscountService {
    private final DiscountRepository discountRepo;
    private final ProductRepository productRepository;
    private final DiscountMapper discountMapper;


    @Override
    public DiscountResponse addDiscount(DiscountRequest discountRequest) {
        var discountEntity = discountMapper.fromDTO(discountRequest);
        var product = productRepository.findProductStatusActivity(discountRequest.getProduct().getId())
                .orElseThrow(() -> new NotDataFound(CakeHouseConstants.NOTDATAFOUNDCATEGORY));

//            var unitPrice = product.getUnitPrice();
//            System.out.println(unitPrice);
//            var discount = discountRequest.getDiscount();
//            var costPrice = unitPrice - unitPrice * discount / 100;
        discountEntity.setProduct(product);
//          discountEntity.getProduct().setUnitPrice(costPrice);

        return discountMapper.toDTO(discountRepo.save(discountEntity));

    }

    @Override
    public List<Discount> getCustomerByStatus() {
        return discountRepo.findByDeactiveStatus();
    }

    @Override
    public void terminatedDiscount(Discount discount) {

        if (LocalDateTime.now().isAfter(discount.getEndDate())) {
            discount.setStatus(Status.DEACTIVE);
        }
        discountRepo.save(discount);

    }

    @Override
    public void activatedDiscount(Discount discount) {
        if (LocalDateTime.now().isAfter(discount.getDiscountDate()) && LocalDateTime.now().isBefore(discount.getEndDate())) {
            discount.setStatus(Status.ACTIVE);
        }
        discountRepo.save(discount);
    }


//                @Override
//                public void updateProductUnitPrice(Discount discount) {
//                  //  var discountEntity= discountRepo.findProductStatusActivity(discount.getId());
////                    var product = productRepository.findProductStatusActivity(discount.getProduct().getId())
////                            .orElseThrow(() -> new NotDataFound(CakeHouseConstants.NOTDATAFOUNDCATEGORY));
//            var unitPrice = discount.getProduct().getUnitPrice();
//            System.out.println(unitPrice);
//            var costPrice = unitPrice - unitPrice * discount.getDiscount() / 100;
//                   // discount.setProduct(product);
//          discount.getProduct().setUnitPrice(costPrice);
//                    discountRepo.save(discount);
//                }
//            }


}


