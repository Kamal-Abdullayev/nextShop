package com.nextShop.product_service.controller;

import com.nextShop.product_service.dto.request.category.CategoryCreatRequestDto;
import com.nextShop.product_service.dto.request.category.CategoryUpdateRequestDto;
import com.nextShop.product_service.dto.response.CategoryResponseDto;
import com.nextShop.product_service.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.PageRequest;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RequiredArgsConstructor
@RestController
@RequestMapping("/api/v1/category")
public class CategoryController {
    private final CategoryService categoryService;

    @GetMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> getCategoryById(@PathVariable("id") String id) {
        return new ResponseEntity<>(categoryService.getCategoryById(id), HttpStatus.OK);
    }

    @GetMapping("/all")
    public ResponseEntity<List<CategoryResponseDto>> getAllCategories(@RequestParam(name = "page", defaultValue = "0") int page,
                                                                      @RequestParam(name = "size", defaultValue = "2" ) int size) {
        return new ResponseEntity<>(categoryService.getAllCategories(PageRequest.of(page, size)) ,HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<String> saveCategory(@RequestBody CategoryCreatRequestDto categoryCreatRequestDto) {
        return new ResponseEntity<>(categoryService.saveCategory(categoryCreatRequestDto), HttpStatus.CREATED);
    }

    @PutMapping("/{id}")
    public ResponseEntity<CategoryResponseDto> updateCategory(@PathVariable("id") String id, @RequestBody CategoryUpdateRequestDto categoryUpdateRequestDto) {
        return new ResponseEntity<>(categoryService.updateCategory(id, categoryUpdateRequestDto), HttpStatus.OK);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<HttpStatus> deleteCategoryById(@PathVariable("id") String id) {
        categoryService.deleteCategoryById(id);
        return new ResponseEntity<>(HttpStatus.NO_CONTENT);
    }

}
