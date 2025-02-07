package com.nextShop.product_service.service;


import com.nextShop.product_service.dto.request.ProductCreateRequestDto;
import com.nextShop.product_service.dto.request.ProductPurchaseRequestDto;
import com.nextShop.product_service.dto.request.ProductUpdateRequestDto;
import com.nextShop.product_service.dto.response.ProductPurchaseResponseDto;
import com.nextShop.product_service.dto.response.ProductResponseDto;
import com.nextShop.product_service.exception.InvalidParameterException;
import com.nextShop.product_service.exception.ProductPurchaseException;
import com.nextShop.product_service.exception.ResourceNotFoundException;
import com.nextShop.product_service.model.Product;
import com.nextShop.product_service.repository.ProductRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Slf4j
@RequiredArgsConstructor
@Service
public class ProductService {
    private final ProductRepository productRepository;
    private final CategoryService categoryService;

    public List<ProductResponseDto> getAllProducts(Pageable pageable) {
        log.info("Get all products");
        return productRepository.findAll(pageable).stream()
                .map(ProductResponseDto::convert)
                .toList();
    }

    public ProductResponseDto getProductById(String productId) {
        log.info("Get product by id: {}", productId);
        return ProductResponseDto.convert(getProductObjectById(productId));
    }

    public Product getProductObjectById(String productId) {
        if (productId == null || productId.isEmpty()) {
            log.error("Get product by id is null or empty");
            throw new InvalidParameterException("Invalid product id");
        }
        return productRepository.findById(productId).orElseThrow(
                () -> new ResourceNotFoundException("Product not found")
        );
    }

    @Transactional
    public List<ProductPurchaseResponseDto> productPurchase(List<ProductPurchaseRequestDto> productPurchaseRequestDtoList) {
        List<Product> purchaseProductList = new ArrayList<>();
        productPurchaseRequestDtoList.forEach(productPurchaseRequest -> {
            Product dbProduct = getProductObjectById(productPurchaseRequest.getProductId());
            if (checkProductAvailability(dbProduct, productPurchaseRequest)) {
                dbProduct.setQuantity(dbProduct.getQuantity() - productPurchaseRequest.getQuantity());
                purchaseProductList.add(dbProduct);
            }
        });

        List<Product> products = productRepository.saveAll(purchaseProductList);
        return products.stream().map(ProductPurchaseResponseDto::convert).toList();
    }

    public ProductResponseDto saveProduct(ProductCreateRequestDto productRequestDto) {
        Product product = Product.builder()
                .name(productRequestDto.getName())
                .price(productRequestDto.getPrice())
                .discountPrice(productRequestDto.getDiscountPrice())
                .description(productRequestDto.getDescription())
                .quantity(productRequestDto.getQuantity())
                .colors(productRequestDto.getColors())
                .sizes(productRequestDto.getSizes())
                .categories(
                        categoryService.getCategoriesByCategoryIdList(productRequestDto.getCategories())
                )
                .build();

        log.info("Product saved successfully. Product: {}", product);
        return ProductResponseDto.convert(productRepository.save(product));
    }

    public ProductResponseDto updateProduct(String productId, ProductUpdateRequestDto productUpdateRequestDto) {
        log.info("Update product by id: {}", productId);
        Product dbProduct = getProductObjectById(productId);

        if (!productUpdateRequestDto.getName().equals(dbProduct.getName())) {
            log.info("Product name changed from {} to {}", dbProduct.getName(), productUpdateRequestDto.getName());
            dbProduct.setName(productUpdateRequestDto.getName());
        }
        if (!productUpdateRequestDto.getDescription().equals(dbProduct.getDescription())) {
            log.info("Product description changed from {} to {}", dbProduct.getDescription(), productUpdateRequestDto.getDescription());
            dbProduct.setDescription(productUpdateRequestDto.getDescription());
        }
        if (productUpdateRequestDto.getPrice() != dbProduct.getPrice()) {
            log.info("Product price changed from {} to {}", dbProduct.getPrice(), productUpdateRequestDto.getPrice());
            dbProduct.setPrice(productUpdateRequestDto.getPrice());
        }
        if (productUpdateRequestDto.getDiscountPrice() != dbProduct.getDiscountPrice()) {
            log.info("Product discount price changed from {} to {}", dbProduct.getDiscountPrice(), productUpdateRequestDto.getDiscountPrice());
            dbProduct.setDiscountPrice(productUpdateRequestDto.getDiscountPrice());
        }
        if (productUpdateRequestDto.getQuantity() != dbProduct.getQuantity()) {
            log.info("Product quantity changed from {} to {}", dbProduct.getQuantity(), productUpdateRequestDto.getQuantity());
            dbProduct.setQuantity(productUpdateRequestDto.getQuantity());
        }
        if (!productUpdateRequestDto.getColors().equals(dbProduct.getColors())) {
            log.info("Product colors list updated from {} to {}", productUpdateRequestDto.getColors(), dbProduct.getColors());
            dbProduct.setColors(productUpdateRequestDto.getColors());
        }
        if (!productUpdateRequestDto.getSizes().equals(dbProduct.getSizes())) {
            log.info("Product size list updated from {} to {}", productUpdateRequestDto.getSizes(), dbProduct.getSizes());
            dbProduct.setSizes(productUpdateRequestDto.getSizes());
        }
        log.info("Product updated successfully");
        return ProductResponseDto.convert(productRepository.save(dbProduct));
    }

    public void deleteProduct(String productId) {
        Product dbProduct = getProductObjectById(productId);
        productRepository.delete(dbProduct);
        log.info("Product deleted successfully with id: {}", productId);
    }

    private boolean checkProductAvailability(Product dbProduct, ProductPurchaseRequestDto productPurchaseRequestDto) {
        if (productPurchaseRequestDto.getQuantity() > dbProduct.getQuantity()) {
            log.error("Product is out of stock. Product id: {}, quantity: {}", dbProduct.getId(), productPurchaseRequestDto.getQuantity());
            throw new ProductPurchaseException("Product is out of stock");
        }
        // TODO: Check if the product is in stock even if there is enough quantity. isProductPublished?
        // TODO: Check based on color and size
        log.info("Product is available. Product id: {}", dbProduct.getId());
        return true;
    }
}
