package com.webshop.products.service.impl;

import com.webshop.products.domain.Product;
import com.webshop.products.dto.ProductsByIdsRequest;
import com.webshop.products.exception.NotFoundException;
import com.webshop.products.repository.ProductRepository;
import com.webshop.products.service.ProductService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Slf4j
@RequiredArgsConstructor
public class ProductServiceImpl implements ProductService {

    private final ProductRepository productRepository;

    @Override
    public Page<Product> findAllProducts(Pageable pageable) {
        return productRepository.findAll(pageable);
    }

    @Override
    public List<Product> findAllProductsByIdIn(ProductsByIdsRequest productsByIdsRequest) {
        return productRepository.findAllByIdIn(productsByIdsRequest.getProducts().stream()
                .map(ProductsByIdsRequest.Product::getProductId)
                .collect(Collectors.toList()));

    }

    @Override
    public Product findProductById(String id) {
        return productRepository.findById(id).orElseThrow(() -> new NotFoundException(String.format(
                "Product with id: %s not found", id)));
    }

    @Override
    public Product saveProduct(Product product) {
        return productRepository.insert(product);
    }

    @Override
    public Product updateProduct(Product product, String id) {
        findProductById(id);
        product.setId(id);
        return productRepository.save(product);
    }

    @Override
    public void deleteProductById(String id) {
        productRepository.deleteById(id);
    }

}
