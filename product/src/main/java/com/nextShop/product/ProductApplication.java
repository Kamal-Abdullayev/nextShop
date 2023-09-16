package com.nextShop.product;

import com.nextShop.product.entity.Category;
import com.nextShop.product.repository.CategoryRepository;
import lombok.AllArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;

@AllArgsConstructor
@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

	CategoryRepository categoryRepository;
	public static void main(String[] args) {
		SpringApplication.run(ProductApplication.class, args);
	}

	@Override
	public void run(String... args) throws Exception {

		Category category1 = new Category("A", null, null, null);
		Category category2 =  new Category("B", category1, null, null);
		List<Category> categoryList = new LinkedList<>();
		categoryList.add(category2);
		category1.setCategoryChildrensList(categoryList);
		Category category3 = new Category("C", category2, null, null);
		List<Category> categoryList2 = new LinkedList<>();
		categoryList.add(category3);
		category2.setCategoryChildrensList(categoryList2);

		categoryRepository.save(category1);
		categoryRepository.save(category2);
		categoryRepository.save(category3);
	}
}
