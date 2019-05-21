package com.webshop.products.repository;

import com.webshop.products.domain.Product;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductRepository extends MongoRepository<Product, String> {

    List<Product> findAllByIdIn(List<String> ids);

}
