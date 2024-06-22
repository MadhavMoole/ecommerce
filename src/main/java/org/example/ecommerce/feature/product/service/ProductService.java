package org.example.ecommerce.feature.product.service;

//region imports
import org.example.ecommerce.database.models.Product;
import org.example.ecommerce.database.repository.ProductRepository;
import org.example.ecommerce.feature.product.model.ProductResponseDTO;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
//endregion

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
            if (products.isEmpty()) {
                logger.info("ProductService => getAllProducts() => No Products Found");
                return new ArrayList<>();
            }
            logger.info("ProductService => getAllProducts() => Products Found {}", products);
            return products.stream()
                    .map(product -> new ProductResponseDTO(
                            product.getName(),
                            product.getShortDescription(),
                            product.getLongDescription(),
                            product.getPrice(),
                            product.getInventory()
                    ))
                    .toList();
        } catch (Exception e) {
            logger.error("ProductService => getAllProducts() => Error {}", e.getMessage());
            return new ArrayList<>();
        }
    }
    //endregion
}
