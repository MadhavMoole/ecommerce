package org.example.ecommerce.feature.product.controller;

//region imports
import org.example.ecommerce.feature.product.model.ProductResponseDTO;
import org.example.ecommerce.feature.product.service.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;
//endregion

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

    //region getAllProducts
    @GetMapping
    public ResponseEntity<List<ProductResponseDTO>> getAllProducts() {
        List<ProductResponseDTO> products = productService.getAllProducts();
        if(products.isEmpty()) {
            return ResponseEntity.noContent().build();
        }
        return ResponseEntity.ok(products);
    }
    //endregion
}
