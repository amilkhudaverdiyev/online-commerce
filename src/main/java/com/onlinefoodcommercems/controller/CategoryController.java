package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.CakeHouseConstants;
import com.onlinefoodcommercems.dto.CategoryDto;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.service.CategoryService;
import com.onlinefoodcommercems.utils.CakeHouseUtils;
import lombok.RequiredArgsConstructor;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;
    @GetMapping("/all")
    public List<CategoryDto> getAllCategory() {
        return  categoryService.findALl();
    }
    @GetMapping("/all-active")
    public List<CategoryDto> getAllCategoryActivated() {
        return  categoryService.findAllByActivated();
    }

    @PostMapping
    public ResponseEntity<String> createCategory(@RequestBody CategoryRequest request) {
        try {
            categoryService.save(request);
            return CakeHouseUtils.getResponseEntity(CakeHouseConstants.SUCCESSFULLY, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CakeHouseUtils.getResponseEntity(CakeHouseConstants.SOMETHING_WENT_WRONG, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @RequestMapping(value = "/deleted-category/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> deletedCategory(@PathVariable Long id) {
        try {
            categoryService.deleteById(id);
            return CakeHouseUtils.getResponseEntity(CakeHouseConstants.DELETE_SUCCESSFULLY, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CakeHouseUtils.getResponseEntity(CakeHouseConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @RequestMapping(value = "/enabled-category/{id}", method = {RequestMethod.GET, RequestMethod.PUT})
    public ResponseEntity<String> enabledCategory(@PathVariable Long id) {
        try {
            categoryService.enableById(id);
            return CakeHouseUtils.getResponseEntity(CakeHouseConstants.ENABLED_SUCCESSFULLY, HttpStatus.OK);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
        return CakeHouseUtils.getResponseEntity(CakeHouseConstants.ERROR, HttpStatus.INTERNAL_SERVER_ERROR);

    }
    @RequestMapping(value = "/findById/{id}", method = {RequestMethod.PUT, RequestMethod.GET})
    public CategoryDto findById(@PathVariable Long id) {
        return categoryService.findById(id);
    }
}