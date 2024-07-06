package org.example.ecommerce.dto;

import org.example.ecommerce.database.models.WebOrder;


public record WebOrderDTO(
        UserDTO userDTO,
        AddressDTO addressDTO
) {
    public static WebOrderDTO fromEntity(WebOrder webOrder) {
        return new WebOrderDTO(
                UserDTO.fromEntity(webOrder.getUser()),
                AddressDTO.fromEntity(webOrder.getAddress())
        );
    }
}
