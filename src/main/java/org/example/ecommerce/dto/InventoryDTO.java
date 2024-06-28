package org.example.ecommerce.dto;

import org.example.ecommerce.database.models.Inventory;

public record InventoryDTO(
        int quantity
) {
    public static InventoryDTO fromEntity(Inventory inventory) {
        return new InventoryDTO(inventory.getQuantity());
    }
}
