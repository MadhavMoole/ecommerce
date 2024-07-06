package org.example.ecommerce.dto.order;

import org.example.ecommerce.database.models.WebOrder;
import org.example.ecommerce.dto.AddressDTO;
import org.example.ecommerce.dto.UserDTO;
import org.example.ecommerce.dto.WebOrderQuantitiesDTO;

import java.util.List;

public record WebOrderResponseDTO(
        UserDTO user,
        AddressDTO address,
        List<WebOrderQuantitiesDTO> webOrderQuantities
) {
    public static WebOrderResponseDTO fromEntity(WebOrder webOrder) {
        return new WebOrderResponseDTO(
                UserDTO.fromEntity(webOrder.getUser()),
                AddressDTO.fromEntity(webOrder.getAddress()),
                webOrder.getQuantities().stream()
                        .map(WebOrderQuantitiesDTO::fromEntity)
                        .toList()
        );
    }
}
