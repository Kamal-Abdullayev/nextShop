package com.nextShop.product;

import com.nextShop.product.entity.Category;
import com.nextShop.product.entity.Color;
import com.nextShop.product.entity.Size;
import com.nextShop.product.repository.CategoryRepository;
import com.nextShop.product.repository.ColorRepository;
import com.nextShop.product.repository.SizeRepository;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.util.LinkedList;
import java.util.List;

@RequiredArgsConstructor
@SpringBootApplication
public class ProductApplication implements CommandLineRunner {

	private final CategoryRepository categoryRepository;
	private final ColorRepository colorRepository;
	private final SizeRepository sizeRepository;
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


		Color color = new Color("red");
		Color color2 = new Color("green");
		Color color3 = new Color("blue");
		color.setActive(true);
		color2.setActive(true);
		color3.setActive(true);
		colorRepository.save(color);
		colorRepository.save(color2);
		colorRepository.save(color3);

		Size size = new Size("S");
		Size size2 = new Size("M");
		Size size3 = new Size("L");
		size.setActive(true);
		size2.setActive(true);
		size3.setActive(true);
		sizeRepository.save(size);
		sizeRepository.save(size2);
		sizeRepository.save(size3);
	}
}
