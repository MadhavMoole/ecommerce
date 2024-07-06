package org.example.ecommerce.service.product;

import org.example.ecommerce.database.models.Product;
import org.example.ecommerce.database.repository.ProductRepository;
import org.example.ecommerce.dto.InventoryDTO;
import org.example.ecommerce.dto.product.ProductResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class ProductService implements IProductService{

    //region declaration
    private ProductRepository productRepository;
    private final Logger logger = LoggerFactory.getLogger(ProductService.class);
    //endregion

    //region di
    @Autowired
    public void setProductRepository(ProductRepository productRepository) {
        this.productRepository = productRepository;
    }
    //endregion

    //region getAllProducts
    @Override
    public List<ProductResponseDTO> getAllProducts() {
        try {
            List<Product> products = productRepository.findAll();
            if (!products.isEmpty()) {
                return products.stream()
                        .map(ProductResponseDTO::fromEntity)
                        .toList();
            }

        } catch (Exception e) {
            logger.error("ProductService => getAllProducts() => Error {}", e.getMessage());
        }
        return null;
    }
    //endregion
}
