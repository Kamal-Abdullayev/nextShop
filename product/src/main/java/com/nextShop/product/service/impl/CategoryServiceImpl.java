package com.nextShop.product.service.impl;

import com.nextShop.product.dto.categoryDto.request.CategoryRequest;
import com.nextShop.product.dto.categoryDto.request.CategoryUpdateRequest;
import com.nextShop.product.dto.categoryDto.response.*;
import com.nextShop.product.entity.base.BaseEntityAudit;
import com.nextShop.product.entity.Category;
import com.nextShop.product.exceprion.CommonErrorCodesException;
import com.nextShop.product.exceprion.ItemCannotBeNullException;
import com.nextShop.product.exceprion.ItemNotFoundException;
import com.nextShop.product.repository.CategoryRepository;
import com.nextShop.product.service.CategoryService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.LinkedList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class CategoryServiceImpl implements CategoryService {
    private final CategoryRepository categoryRepository;

    @Override
    public List<CategoryResponse> getAllCategories() {
        return categoryRepository.findAll()
                .stream()
                .filter(BaseEntityAudit::isActive)
                .map(CategoryResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public List<ChildCategoryResponse> getChildCategories(String id) {
        return categoryRepository.findChildrenCategories(id)
                .stream()
//                .filter(BaseEntityAudit::isActive) // Filter active child categories
                .map(ChildCategoryResponse::from)
                .collect(Collectors.toList());
    }

    //Test it
    @Override
    public List<CategoryProductResponse> getCategoryProducts(String id) {
        return categoryRepository.findCategoryProductsByCategoryId(id)
                .stream()
//                .filter(BaseEntityAudit::isActive)
                .map(CategoryProductResponse::from)
                .collect(Collectors.toList());
    }

    @Override
    public CategoryResponse getCategoryById(String id) {
        Category category = findCategory(id);
        if (!category.isActive()) {
            throw new ItemNotFoundException("Category");
        }
        return CategoryResponse.from(findCategory(id));
    }

    @Transactional
    @Override
    public String saveCategory(CategoryRequest categoryRequest) {
        Category category = new Category(
                categoryRequest.getCategoryName(),
                null,
                null,
                categoryRequest.getProductList());

        if (categoryRequest.getParentCategoryId() != null) {
            List<Category> parentCategorysChildrenList = new LinkedList<>();

            Category patentCategory = findCategory(categoryRequest.getParentCategoryId());
            parentCategorysChildrenList.add(patentCategory);

            patentCategory.setCategoryChildrensList(parentCategorysChildrenList);
            category.setParentCategory(patentCategory);
            categoryRepository.save(patentCategory);
        }

        categoryRepository.save(category);
        return category.getId();
    }

    @Transactional
    @Override
    public CategoryUpdateResponse updateCategory(String id, CategoryUpdateRequest categoryUpdateRequest) {
        Category dbCategory = findCategory(id);

        if (!dbCategory.isActive()) throw new ItemNotFoundException("Category");
        if (categoryUpdateRequest.getCategoryName() == null) throw new ItemCannotBeNullException("Category name");

        dbCategory.setCategoryName(categoryUpdateRequest.getCategoryName());

        String parentCategoryId = null;
        if (categoryUpdateRequest.getParentCategoryId() != null) {
            Category parentCategory = findCategory(categoryUpdateRequest.getParentCategoryId());

            parentCategory.getCategoryChildrensList().forEach(c-> {
                if (c.equals(dbCategory)) {
                    c.setParentCategory(parentCategory);
                }
            });
            parentCategoryId = parentCategory.getId();
            dbCategory.setParentCategory(parentCategory);
        }
        categoryRepository.save(dbCategory);

        System.out.println(parentCategoryId);
        return new CategoryUpdateResponse(
                dbCategory.getId(),
                parentCategoryId,
                dbCategory.getCategoryName()
        );
    }

    @Transactional
    @Override
    public void deactivateCategory(String id) {
        Category category = findCategory(id);
        if (!category.isActive()) throw new ItemNotFoundException("Category");
        category.setActive(false);
        categoryRepository.save(category);
    }

    @Transactional
    @Override
    public void deleteCategory(String id) {
        Category category = findCategory(id);
        if (category.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_NOT_DEACTIVATED);
        }
        categoryRepository.delete(category);
    }

    @Transactional
    @Override
    public void activateCategory(String id) {
        Category category = findCategory(id);
        if (category.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_STATUS_ALREADY_ACTIVE);
        }
        category.setActive(true);
        categoryRepository.save(category);
    }

    @Override
    public List<ParentCategoryResponse> getAllDeactivatedCategories() {
        return categoryRepository.findAll()
                .stream()
                .filter(c -> !c.isActive())
                .map(ParentCategoryResponse::from)
                .collect(Collectors.toList());
    }

    private Category findCategory(String id) {
        return categoryRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Category"));
    }
}
