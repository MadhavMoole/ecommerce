package org.example.ecommerce.dto;

import org.example.ecommerce.database.models.Product;

public record ProductDTO(
        String name,
        String shortDescription,
        String longDescription,
        double price
) {

    public static ProductDTO fromEntity(Product product) {
        return new ProductDTO(
                product.getName(),
                product.getShortDescription(),
                product.getLongDescription(),
                product.getPrice()
        );
    }

}
