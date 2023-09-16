package com.nextShop.product.service.impl;

import com.nextShop.product.dto.colorDto.ColorResponse;
import com.nextShop.product.dto.converter.ColorDtoConverter;
import com.nextShop.product.dto.productDto.ProductResponse;
import com.nextShop.product.dto.productDto.ProductSaveRequest;
import com.nextShop.product.entity.*;
import com.nextShop.product.repository.ProductRepository;
import com.nextShop.product.service.ColorService;
import com.nextShop.product.service.ProductService;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ColorService colorService;

    @Override
    public List<ProductResponse> getProductList() {
        return productRepository.findAll().stream().map(ProductResponse::from).collect(Collectors.toList());
    }

    @Override
    public ProductResponse getProductById(String id) {
        Product dbProduct = productRepository.findById(id).orElseThrow(() -> new RuntimeException("Product not found!!!"));
        return ProductResponse.from(dbProduct);
    }

    @Override
    public void saveProduct(ProductSaveRequest productSaveRequest) {
        List<Color> colorList = new ArrayList<>();
        List<Size> sizeList = new ArrayList<>();
        List<Image> imageList = new ArrayList<>();
        List<Category> categoryList = new ArrayList<>();

        Product product = new Product(
                productSaveRequest.getName(),
                productSaveRequest.getPrice(),
                0.00,
                productSaveRequest.getDescription(),
                productSaveRequest.getRate(),
                sizeList,
                imageList,
                categoryList,
                colorList
        );



        productRepository.save(product);
    }


}
