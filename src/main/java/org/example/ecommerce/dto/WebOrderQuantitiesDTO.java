package org.example.ecommerce.dto;

import org.example.ecommerce.database.models.WebOrderQuantities;

public record WebOrderQuantitiesDTO(
        ProductDTO productDTO,
        int quantity
) {
    public static WebOrderQuantitiesDTO fromEntity(WebOrderQuantities webOrderQuantities) {
        return new WebOrderQuantitiesDTO(
                ProductDTO.fromEntity(webOrderQuantities.getProduct()),
                webOrderQuantities.getQuantity()
        );
    }
}
