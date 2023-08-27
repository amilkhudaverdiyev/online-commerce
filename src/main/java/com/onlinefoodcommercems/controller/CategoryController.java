package com.onlinefoodcommercems.controller;

import com.onlinefoodcommercems.constants.ResponseMessage;
import com.onlinefoodcommercems.dto.request.CategoryRequest;
import com.onlinefoodcommercems.dto.response.CategoryResponse;
import com.onlinefoodcommercems.dto.response.ResponseDetail;
import com.onlinefoodcommercems.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/category")
@RequiredArgsConstructor
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    @PreAuthorize("hasAuthority('ADMIN')")
    public List<CategoryResponse> getAllCategory() {
        return categoryService.findAll();
    }

    @GetMapping("/count")
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllCategoryCount() {
        return categoryService.getAllCategoryCount();
    }


    @PostMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail createCategory(@RequestBody @Valid CategoryRequest request) {
        categoryService.save(request);
        return ResponseDetail.builder()
                .message(ResponseMessage.ADD_SUCCESSFULLY)
                .status(HttpStatus.CREATED.getReasonPhrase())
                .statusCode(HttpStatus.CREATED.value()).build();
    }

    @PutMapping("/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail updateCategory(@PathVariable Long id,
                                         @RequestBody @Valid CategoryRequest request) {
        categoryService.update(id, request);
        return ResponseDetail.builder()
                .message(ResponseMessage.UPDATE_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value()).build();
    }

    @DeleteMapping(value = "/deleted-category/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail deletedCategory(@PathVariable Long id) {
        categoryService.deleteById(id);
        return ResponseDetail.builder()
                .message(ResponseMessage.DELETE_SUCCESSFULLY)
                .status(HttpStatus.NO_CONTENT.getReasonPhrase())
                .statusCode(HttpStatus.NO_CONTENT.value()).build();
    }

    @PutMapping(value = "/enabled-category/{id}")
    @PreAuthorize("hasAuthority('ADMIN')")
    public ResponseDetail enabledCategory(@PathVariable Long id) {
        categoryService.enableById(id);
        return ResponseDetail.builder()
                .message(ResponseMessage.ENABLED_SUCCESSFULLY)
                .status(HttpStatus.OK.getReasonPhrase())
                .statusCode(HttpStatus.OK.value()).build();
    }
}

