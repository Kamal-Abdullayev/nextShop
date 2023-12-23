package com.nextShop.product.controller;

import com.nextShop.product.dto.categoryDto.request.CategoryRequest;
import com.nextShop.product.dto.categoryDto.request.CategoryUpdateRequest;
import com.nextShop.product.dto.categoryDto.response.*;
import com.nextShop.product.entity.base.BaseResponse;
import com.nextShop.product.exceprion.BaseException;
import com.nextShop.product.repository.CategoryRepository;
import com.nextShop.product.service.CategoryService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;
    private final CategoryRepository categoryRepository;

    @GetMapping("/all")
    public BaseResponse<List<CategoryResponse>> findAllCategories() {
        throw BaseException.unexpected();
//        return BaseResponse.success(categoryService.getAllCategories());
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
    public ResponseEntity<HttpStatus> saveCategory(@Valid @RequestBody CategoryRequest categoryRequest) {
        categoryService.saveCategory(categoryRequest);
        return new ResponseEntity<>(HttpStatus.CREATED);
    }

    // Change it
    @PutMapping("{id}")
    public ResponseEntity<CategoryUpdateResponse> updateCategory(@PathVariable("id") String id,
                                                                 @Valid @RequestBody CategoryUpdateRequest categoryUpdateRequest) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryUpdateRequest), HttpStatus.OK);
    }

    @PatchMapping("/deactivate/{id}")
    public ResponseEntity<HttpStatus> deactivateCategory(@PathVariable("id") String id) {
        categoryService.deactivateCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @PatchMapping("/activate/{id}")
    public ResponseEntity<HttpStatus> activateCategory(@PathVariable("id") String id) {
        categoryService.activateCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategory(@PathVariable("id") String id) {
        categoryService.deleteCategory(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

    @GetMapping("/all/deactivated")
    public BaseResponse<List<ParentCategoryResponse>> getAllDeactivatedCategories() {
        return BaseResponse.success(categoryService.getAllDeactivatedCategories());
    }

    @GetMapping("/test")
    public BaseResponse<List<String>> test(@RequestBody List<String> idList) {
        return BaseResponse.success(categoryRepository.getInvalidCategoriesIdList(idList));
    }
}
