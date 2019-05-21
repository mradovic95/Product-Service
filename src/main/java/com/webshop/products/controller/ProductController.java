package com.webshop.products.controller;

import com.webshop.products.domain.Product;
import com.webshop.products.dto.ProductsByIdsRequest;
import com.webshop.products.secutiry.CheckSecurity;
import com.webshop.products.service.ProductService;
import io.swagger.annotations.ApiOperation;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestHeader;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;
import java.util.List;

@RestController
@RequestMapping("/products")
@RequiredArgsConstructor
public class ProductController {

    private final ProductService productService;

    @ApiOperation(value = "Get all products")
    @GetMapping
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Page<Product>> getAllProducts(@RequestHeader("Authorization") String authorization, Pageable pageable) {
        return new ResponseEntity<>(productService.findAllProducts(pageable), HttpStatus.OK);
    }

    @ApiOperation(value = "Get all products by ids")
    @PostMapping("/byIds")
    public ResponseEntity<List<Product>> getAllProductsByIds(@RequestBody @Valid ProductsByIdsRequest productsByIdsRequest) {
        return new ResponseEntity<>(productService.findAllProductsByIdIn(productsByIdsRequest), HttpStatus.OK);
    }

    @ApiOperation(value = "Get product by id")
    @GetMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN", "ROLE_USER"})
    public ResponseEntity<Product> getAllProducts(@RequestHeader("Authorization") String authorization, @PathVariable String id) {
        return new ResponseEntity<>(productService.findProductById(id), HttpStatus.OK);
    }

    @ApiOperation(value = "Save product")
    @PostMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Product> saveProduct(@RequestHeader("Authorization") String authorization, @RequestBody @Valid Product product) {
        return new ResponseEntity<>(productService.saveProduct(product), HttpStatus.CREATED);
    }

    @ApiOperation(value = "Update product")
    @PutMapping
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity<Product> updateProduct(@RequestHeader("Authorization") String authorization, @RequestBody @Valid Product product, @PathVariable String id) {
        return new ResponseEntity<>(productService.updateProduct(product, id), HttpStatus.OK);
    }

    @ApiOperation(value = "Delete product")
    @DeleteMapping("/{id}")
    @CheckSecurity(roles = {"ROLE_ADMIN"})
    public ResponseEntity deleteProduct(@RequestHeader("Authorization") String authorization, @PathVariable String id) {
        productService.deleteProductById(id);
        return new ResponseEntity(HttpStatus.NO_CONTENT);
    }

}
