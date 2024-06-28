package org.example.ecommerce.dto;

import org.example.ecommerce.database.models.Address;

public record AddressDTO(
        String addressLine1,
        String addressLine2,
        String city,
        String country
) {
    public static AddressDTO fromEntity(Address address) {
        return new AddressDTO(
                address.getAddressLine1(),
                address.getAddressLine2(),
                address.getCity(),
                address.getCountry()
        );
    }
}
