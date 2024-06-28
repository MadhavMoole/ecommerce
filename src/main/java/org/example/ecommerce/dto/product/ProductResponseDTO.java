package org.example.ecommerce.dto.product;

import org.example.ecommerce.database.models.Product;
import org.example.ecommerce.dto.InventoryDTO;

public record ProductResponseDTO(
        String name,
        String shortDescription,
        String longDescription,
        double price,
        InventoryDTO inventory
) {
    public static ProductResponseDTO fromEntity(Product product) {
        return new ProductResponseDTO(
                product.getName(),
                product.getShortDescription(),
                product.getLongDescription(),
                product.getPrice(),
                InventoryDTO.fromEntity(product.getInventory())
        );
    }
}
