package org.example.ecommerce.service.product;

import org.example.ecommerce.dto.product.ProductResponseDTO;

import java.util.List;

public interface IProductService {
    List<ProductResponseDTO> getAllProducts();
}
