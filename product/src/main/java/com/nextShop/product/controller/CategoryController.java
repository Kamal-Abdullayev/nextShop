package com.nextShop.product.controller;

import com.nextShop.product.dto.categoryDto.request.CategoryRequest;
import com.nextShop.product.dto.categoryDto.request.CategoryUpdateRequest;
import com.nextShop.product.dto.categoryDto.response.*;
import com.nextShop.product.entity.base.BaseResponse;
import com.nextShop.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/all")
    public BaseResponse<List<CategoryResponse>> findAllCategories() {
        return BaseResponse.success(categoryService.getAllCategories());
    }

    @GetMapping("/{id}/categories")
    public BaseResponse<List<ChildCategoryResponse>> getChildCategories(@PathVariable("id") String id) {
        return BaseResponse.success(categoryService.getChildCategories(id));
    }

    @GetMapping("/{id}/products")
    public BaseResponse<List<CategoryProductResponse>> getCategoryProducts(@PathVariable("id") String id) {
        return BaseResponse.success(categoryService.getCategoryProducts(id));
    }

    @GetMapping("/{id}")
    public BaseResponse<CategoryResponse> findCategoryById(@PathVariable("id") String id) {
        return BaseResponse.success(categoryService.getCategoryById(id));
    }

    @PostMapping
    public BaseResponse<String> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        return new BaseResponse<>(HttpStatus.CREATED, null, categoryService.saveCategory(categoryRequest));
    }

    // Change it
    @PutMapping("{id}")
    public BaseResponse<CategoryUpdateResponse> updateCategory(@PathVariable("id") String id,
                                                                 @Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        return new BaseResponse<>(HttpStatus.OK, null, categoryService.updateCategory(id, categoryUpdateRequest));
    }

    @PatchMapping("/deactivate/{id}")
    public BaseResponse<HttpStatus> deactivateCategory(@PathVariable("id") String id) {
        categoryService.deactivateCategory(id);
        return new BaseResponse<>(HttpStatus.NO_CONTENT, null, null);
    }

    @PatchMapping("/activate/{id}")
    public BaseResponse<HttpStatus> activateCategory(@PathVariable("id") String id) {
        categoryService.activateCategory(id);
        return new BaseResponse<>(HttpStatus.NO_CONTENT, null, null);
    }

    @DeleteMapping("/{id}")
    public BaseResponse<HttpStatus> deleteCategory(@PathVariable("id") String id) {
        categoryService.deleteCategory(id);
        return new BaseResponse<>(HttpStatus.NO_CONTENT, null, null);
    }

    @GetMapping("/all/deactivated")
    public BaseResponse<List<ParentCategoryResponse>> getAllDeactivatedCategories() {
        return BaseResponse.success(categoryService.getAllDeactivatedCategories());
    }
}
