package org.example.ecommerce.controllers;

import org.example.ecommerce.dto.ProductDTO;
import org.example.ecommerce.dto.product.ProductResponseDTO;
import org.example.ecommerce.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/product/v1")
public class ProductController {

    //region declaration
    private final ProductService productService;
    //endregion

    //region di
    @Autowired
    public ProductController(ProductService productService) {
        this.productService = productService;
    }
    //endregion

    //region getAllProducts controller
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        return ResponseEntity.ok(products);
    }
    //endregion

    //region getProductById controller
    @GetMapping("/{id}")
    public ResponseEntity<ProductDTO> getProductById(@PathVariable("id") Long id) {
        var productDTO = productService.getProductById(id);
        return ResponseEntity.ok(productDTO);
    }
    //endregion
}
