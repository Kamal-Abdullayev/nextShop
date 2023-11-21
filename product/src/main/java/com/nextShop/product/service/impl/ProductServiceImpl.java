package com.nextShop.product.service.impl;

import com.nextShop.product.dto.categoryDto.response.CategoryResponse;
import com.nextShop.product.dto.productDto.ProductResponse;
import com.nextShop.product.dto.productDto.ProductSaveRequest;
import com.nextShop.product.dto.productDto.ProductUpdateRequest;
import com.nextShop.product.entity.*;
import com.nextShop.product.exceprion.CommonErrorCodesException;
import com.nextShop.product.exceprion.ItemNotFoundException;
import com.nextShop.product.repository.ProductRepository;
import com.nextShop.product.service.*;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.stream.Collectors;

@RequiredArgsConstructor
@Service
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;
    private final ColorService colorService;
    private final SizeService sizeService;
    private final ImageService imageService;
    private final CategoryService categoryService;

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
    public Product getProductObjectById(String id) {
        Product dbProduct = findProduct(id);
        if (!dbProduct.isActive()) throw new ItemNotFoundException("Product");
        return dbProduct;
    }

    @Override
    public void saveProduct(ProductSaveRequest productSaveRequest) {
        Size size = sizeService.findSizeObject(productSaveRequest.getSizeId());
        Image image = imageService.getImageObject(productSaveRequest.getImageId());
        Color color = colorService.getColorObject(productSaveRequest.getColorId());
        List<Category> categoryList = new ArrayList<>();

        productSaveRequest.getCategoryIdList().forEach(categoryId -> {
            Category dbCategory = categoryService.getCategoryObjectById(categoryId);
            categoryList.add(dbCategory);
        });
        Product product = new Product(
                productSaveRequest.getName(),
                productSaveRequest.getPrice(),
                productSaveRequest.getDiscountPrice(),
                productSaveRequest.getDescription(),
                List.of(size),
                List.of(image),
                categoryList,
                List.of(color)
        );
        productRepository.save(product);
    }


    @Override
    public ProductResponse updateProduct(String id, ProductUpdateRequest productUpdateRequest) {
        Product dbProduct = findProduct(id);
        if (!dbProduct.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.DELETED_ITEM_CANT_BE_USED);
        }
        if (Objects.nonNull(productUpdateRequest.getName()) && !productUpdateRequest.getName().isEmpty()) {
            dbProduct.setName(productUpdateRequest.getName());
        }
        if (productUpdateRequest.getPrice() != dbProduct.getPrice()) {
            dbProduct.setPrice(productUpdateRequest.getPrice());
        }
        if (productUpdateRequest.getDiscountPrice() != dbProduct.getDiscountPrice()) {
            dbProduct.setDiscountPrice(productUpdateRequest.getDiscountPrice());
        }
        if (Objects.nonNull(productUpdateRequest.getDescription()) && !productUpdateRequest.getDescription().isEmpty()) {
            dbProduct.setDescription(productUpdateRequest.getDescription());
        }

        productRepository.save(dbProduct);
        return ProductResponse.from(dbProduct);
    }

    @Override
    public void deactivateProduct(String id) {
        Product product = findProduct(id);
        if (!product.isActive()) throw new ItemNotFoundException("Product");
        product.setActive(false);
        productRepository.save(product);
    }

    @Override
    public void deleteProduct(String id) {
        Product product = findProduct(id);
        if (product.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_NOT_DEACTIVATED);
        }
        productRepository.delete(product);
    }

    @Override
    public void activateProduct(String id) {
        Product product = findProduct(id);
        if (product.isActive()) {
            throw new RuntimeException(CommonErrorCodesException.ITEM_STATUS_ALREADY_ACTIVE);
        }
        product.setActive(true);
        productRepository.save(product);
    }

    @Override
    public List<ProductResponse> getAllDeactivatedProducts() {
        return productRepository.findAll()
                .stream()
                .filter(c -> !c.isActive())
                .map(ProductResponse::from)
                .collect(Collectors.toList());
    }


    private Product findProduct(String id) {
        return productRepository.findById(id).orElseThrow(() -> new ItemNotFoundException("Product"));
    }

}
