package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.ItemResponse;
import com.onlinefoodcommercems.dto.ProductDto;
import com.onlinefoodcommercems.service.CategoryService;
import com.onlinefoodcommercems.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/api/home")
@RequiredArgsConstructor
public class HomeController {
    private final ProductService productService;
    private final CategoryService categoryService;

    @GetMapping("/product-all")
    public Page<ProductDto> getAllProducts(Pageable pageable) {
        return productService.getAllProducts(pageable);
    }

    @GetMapping("/category-id/{id}")
    public List<ItemResponse> findProductsByCategory(@PathVariable Long id) {
        return productService.findByCategoryId(id);
    }

    @GetMapping("/search")
    public List<ProductDto> searchProduct(@RequestParam String keyword) {
        return productService.searchProducts(keyword);
    }
    @GetMapping("/category-all")
    public List<CategoryDto> getAllCategoryActivated() {
        return categoryService.findAllByActivated();
    }
}