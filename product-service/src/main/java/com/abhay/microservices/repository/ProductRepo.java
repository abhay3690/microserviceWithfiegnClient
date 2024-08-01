package com.abhay.microservices.repository;

import com.abhay.microservices.model.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface ProductRepo extends MongoRepository<Product,String> {
}
