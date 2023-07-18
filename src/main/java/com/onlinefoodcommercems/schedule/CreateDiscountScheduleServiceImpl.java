//package com.onlinefoodcommercems.schedule;
//
//import com.onlinefoodcommercems.constants.CakeHouseConstants;
//import com.onlinefoodcommercems.dto.CategoryDto;
//import com.onlinefoodcommercems.dto.response.DiscountResponse;
//import com.onlinefoodcommercems.entity.Category;
//import com.onlinefoodcommercems.entity.Discount;
//import com.onlinefoodcommercems.entity.Product;
//import com.onlinefoodcommercems.enums.Status;
//import com.onlinefoodcommercems.exception.NotDataFound;
//import com.onlinefoodcommercems.mapper.DiscountMapper;
//import com.onlinefoodcommercems.repository.CategoryRepository;
//import com.onlinefoodcommercems.repository.DiscountRepository;
//import com.onlinefoodcommercems.repository.ProductRepository;
//import lombok.RequiredArgsConstructor;
//import org.springframework.stereotype.Service;
//
//import java.util.List;
//
//@Service
//@RequiredArgsConstructor
//public class CreateDiscountScheduleServiceImpl implements CreateDiscountScheduleService {
//private final CategoryRepository categoryRepository;
//private final ProductRepository productRepository;
//
//    @Override
//    public List<Product> getProductByStatus() {
//        return productRepository.findByStatus(Status.ACTIVE);
//    }
//    @Override
//    public List<Category> getCategoryByStatus() {
//        return categoryRepository.findByStatus(Status.DEACTIVE);
//    }
//
//    @Override
//    public void updateProductStatus(Product product) {
//        product.setStatus(Status.DEACTIVE);
//        productRepository.save(product);
//    }
//}
//
//
