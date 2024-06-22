package org.example.ecommerce.feature.product.service;

import org.example.ecommerce.feature.product.model.ProductResponseDTO;

import java.util.List;

public interface IProductService {
    List<ProductResponseDTO> getAllProducts();
}
