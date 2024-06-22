package org.example.ecommerce.feature.order.model;

import org.example.ecommerce.database.models.Address;
import org.example.ecommerce.database.models.User;
import org.example.ecommerce.database.models.WebOrderQuantities;

import java.util.List;

public record WebOrderResponseDTO(
        User user,
        Address address,
        List<WebOrderQuantities> webOrderQuantities
) {
}
