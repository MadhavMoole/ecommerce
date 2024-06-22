package org.example.ecommerce.feature.product.model;

import org.example.ecommerce.database.models.Inventory;

public record ProductResponseDTO(
        String name,
        String shortDescription,
        String longDescription,
        double price,
        Inventory inventory
) {
}
