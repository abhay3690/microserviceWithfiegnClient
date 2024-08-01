package com.abhay.microservices.service;

import com.abhay.microservices.dto.ProductRequest;
import com.abhay.microservices.dto.ProductResponse;
import com.abhay.microservices.model.Product;
import com.abhay.microservices.repository.ProductRepo;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
@Slf4j
public class ProductService {
    private final ProductRepo productRepo;
    public ProductResponse createProduct(ProductRequest productRequest){
        Product product = Product.builder()
                .name(productRequest.name())
                .description(productRequest.description())
                .price(productRequest.price())
                .build();
        productRepo.save(product);
        log.info("Product created Successfully");
        return new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice());
    }
    public List<ProductResponse> getAllProduct(){
        return productRepo.findAll().stream().map(product -> new ProductResponse(product.getId(),product.getName(),product.getDescription(),product.getPrice())).toList();
    }
}
