package com.nextShop.product.repository;

import com.nextShop.product.entity.Category;
import com.nextShop.product.entity.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface CategoryRepository extends JpaRepository<Category, String> {
    @Query("select c.categoryChildrensList from Category c")
    List<Category> findChildrenCategories(String id);
    @Query("select c.productList from Category c")
    List<Category> findCategoryProductsByCategoryId(String id);

    @Query("SELECT c.id FROM Category c WHERE c.id NOT IN :categoryIdList")
    List<String> getInvalidCategoriesIdList(@Param("categoryIdList") List<String> categoryIdList);
}
