package org.example.ecommerce.service.product;

import org.example.ecommerce.database.models.Product;
import org.example.ecommerce.database.repository.ProductRepository;
import org.example.ecommerce.dto.ProductDTO;
import org.example.ecommerce.dto.product.ProductResponseDTO;
import org.example.ecommerce.exception.ProductNotFoundException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
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
            } else {
                logger.error("ProductService => getAllProducts => No Products Found");
                throw new ProductNotFoundException("No Products Found");
            }

        } catch (Exception e) {
            logger.error("ProductService => getAllProducts() => Error {}", e.getMessage());
        }
        return new ArrayList<>();
    }
    //endregion

    //region getProductById
    @Override
    public ProductDTO getProductById(Long id) {
        try {
            var opProduct = productRepository.findById(id);
            if(opProduct.isEmpty()) {
                logger.error("ProductService => getAllProducts => No Product found with the id: {}", id);
                throw new ProductNotFoundException("No Product found with the id:" + id);
            } else {
                Product product = opProduct.get();
                return new ProductDTO(
                    product.getName(), 
                    product.getShortDescription(), 
                    product.getLongDescription(), 
                    product.getPrice()
                    );
            }
        } catch (Exception e) {
            logger.error("ProductService => getProductById() => Error: {}", e);
        }
        return new ProductDTO(null, null, null, 0);
    }
    //endregion
}
