package org.example.ecommerce.service.product;

import org.example.ecommerce.dto.ProductDTO;
import org.example.ecommerce.dto.product.ProductResponseDTO;
import org.example.ecommerce.exception.ProductNotFoundException;

import java.util.List;

public interface IProductService {
    List<ProductResponseDTO> getAllProducts() throws ProductNotFoundException;
    ProductDTO getProductById(Long id) throws ProductNotFoundException;
}
