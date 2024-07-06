package org.example.ecommerce.dto.authentication.myProfile;

import org.example.ecommerce.dto.AddressDTO;

import java.util.List;

public record MyProfileResponseDTO(
        String username,
        String email,
        String firstname,
        String lastname,
        List<AddressDTO> addresses
) {

}

