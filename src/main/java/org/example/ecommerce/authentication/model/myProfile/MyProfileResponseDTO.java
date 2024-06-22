package org.example.ecommerce.authentication.model.myProfile;

import org.example.ecommerce.database.models.Address;

import java.util.List;

public record MyProfileResponseDTO(
        String username,
        String email,
        String firstname,
        String lastname,
        List<Address> addresses
) {

}
