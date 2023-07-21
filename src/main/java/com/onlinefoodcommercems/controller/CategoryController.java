package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.Messages;
import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.service.CategoryService;
import com.onlinefoodcommercems.utils.MessageUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    public List<CategoryResponse> getAllCategory() {
        return categoryService.findAll();
    }

    @GetMapping("/count")
    public String getAllCategoryCount() {
        return categoryService.getAllCategoryCount();
    }

    @GetMapping("/all-active")
    public List<CategoryDto> getAllCategoryActivated() {
        return categoryService.findAllByActivated();
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequest request) {
        categoryService.save(request);
        return MessageUtils.getResponseEntity(Messages.ADD_SUCCESSFULLY, HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<String> updateCategory(@PathVariable Long id,
                                                 @RequestBody CategoryRequest request) {
        categoryService.update(id, request);
        return MessageUtils.getResponseEntity(Messages.UPDATE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/deleted-category/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> deletedCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return MessageUtils.getResponseEntity(Messages.DELETE_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/enabled-category/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> enabledCategory(@PathVariable Long id) {
        categoryService.enableById(id);
        return MessageUtils.getResponseEntity(Messages.ENABLED_SUCCESSFULLY, HttpStatus.OK);
    }

    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public CategoryDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }
}