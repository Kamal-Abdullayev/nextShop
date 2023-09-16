package com.nextShop.product.service;

import com.nextShop.product.dto.categoryDto.request.CategoryRequest;
import com.nextShop.product.dto.categoryDto.request.CategoryUpdateRequest;
import com.nextShop.product.dto.categoryDto.response.*;

import java.util.List;

public interface CategoryService {
    List<CategoryResponse> getAllCategories();
    List<ChildCategoryResponse>  getChildCategories(String id);
    List<CategoryProductResponse> getCategoryProducts(String id);
    CategoryResponse getCategoryById(String id);
    String saveCategory(CategoryRequest categoryRequest);
    CategoryUpdateResponse updateCategory(String id, CategoryUpdateRequest categoryUpdateRequest);
    void deactivateCategory(String id);
    void deleteCategory(String id);
    void activateCategory(String id);
    List<ParentCategoryResponse> getAllDeactivatedCategories();

}
