package org.example.ecommerce.feature.product.service;

import org.example.ecommerce.feature.product.model.ProductResponseDTO;
import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IProductService {
    List<ProductResponseDTO> getAllProducts();
}
