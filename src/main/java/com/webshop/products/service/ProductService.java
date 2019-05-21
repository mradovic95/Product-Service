package com.webshop.products.service;

import com.webshop.products.domain.Product;
import com.webshop.products.dto.ProductsByIdsRequest;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;

import java.util.List;

public interface ProductService {

    Page<Product> findAllProducts(Pageable pageable);

    List<Product> findAllProductsByIdIn(ProductsByIdsRequest productsByIdsRequest);

    Product findProductById(String id);

    Product saveProduct(Product product);

    Product updateProduct(Product product, String id);

    void deleteProductById(String id);

}
