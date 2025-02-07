package com.nextShop.product_service.service;

import com.nextShop.product_service.dto.request.category.CategoryCreatRequestDto;
import com.nextShop.product_service.dto.request.category.CategoryUpdateRequestDto;
import com.nextShop.product_service.dto.response.CategoryResponseDto;
import com.nextShop.product_service.exception.InvalidParameterException;
import com.nextShop.product_service.exception.ResourceNotFoundException;
import com.nextShop.product_service.model.Category;
import com.nextShop.product_service.repository.CategoryRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Slf4j
@RequiredArgsConstructor
@Service
public class CategoryService {
    private final CategoryRepository categoryRepository;

    public List<CategoryResponseDto> getAllCategories(Pageable pageable) {
        log.info("getAllCategories method called");
        return categoryRepository.findAll(pageable).stream()
                .map(CategoryResponseDto::convert)
                .collect(Collectors.toList());
    }

    public CategoryResponseDto getCategoryById(String categoryId) {
        log.info("Get category by id: {}", categoryId);
        return CategoryResponseDto.convert(getCategoryObjectById(categoryId));
    }

    public Category getCategoryObjectById(String categoryId) {
        if (categoryId == null || categoryId.isEmpty()) {
            log.error("Invalid category id provided, categoryId: {}", categoryId);
            throw new InvalidParameterException("Invalid category id");
        }
        log.info("Get category object by id: {}", categoryId);
        return categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("Category not found"));
    }

    public Set<Category> getCategoriesByCategoryIdList(Set<String> categoryIdList) {
        if (categoryIdList == null || categoryIdList.isEmpty()) {
            throw new InvalidParameterException("Invalid category id");
        }
        Set<Category> categories = new HashSet<>();
        categoryIdList.forEach(categoryId -> {
            categories.add(getCategoryObjectById(categoryId));
        });
        log.info("Get categories by id: {}", categories);
        return categories;
    }
    public String saveCategory(CategoryCreatRequestDto categoryDto) {
        if (categoryDto.getCategoryName() == null || categoryDto.getCategoryName().isEmpty()) {
            log.error("Invalid category name provided. Category name: {}", categoryDto.getCategoryName());
            throw new InvalidParameterException("Invalid category name");
        }

        Category newCategory = Category.builder()
                .categoryName(categoryDto.getCategoryName())
                .build();

        log.info("Save category: {}", newCategory);
        return categoryRepository.save(newCategory).getId();
    }

    public CategoryResponseDto updateCategory(String categoryId, CategoryUpdateRequestDto categoryDto) {
        if (categoryDto.getCategoryName() == null || categoryDto.getCategoryName().isEmpty()) {
            log.error("Invalid category name provided for update. Category name: {}", categoryDto.getCategoryName());
            throw new InvalidParameterException("Invalid category name");
        }
        Category dbCategory = getCategoryObjectById(categoryId);

        if (!categoryDto.getCategoryName().equals(dbCategory.getCategoryName())) {
            log.info("Category name changed from {} to {}", categoryDto.getCategoryName(), dbCategory.getCategoryName());
            dbCategory.setCategoryName(categoryDto.getCategoryName());
        }
        Category updatedCategory = categoryRepository.save(dbCategory);
        log.info("Category updated successfully");
        return CategoryResponseDto.convert(updatedCategory);
    }

    public void deleteCategoryById(String categoryId) {
        Category dbCategory = getCategoryObjectById(categoryId);
        categoryRepository.delete(dbCategory);
        log.info("Category deleted successfully with id {}", categoryId);
    }
}
