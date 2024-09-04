package org.example.ecommerce.service.address;

import java.util.List;

import org.example.ecommerce.database.models.Address;
import org.example.ecommerce.exception.NoAddressFoundException;

public interface IAddressService {

    List<Address> getByUserId(Long userId) throws NoAddressFoundException;
    Address putAddress(Long userId, Address address);
    Address updateAddress(Long userId, Address address) throws NoAddressFoundException;
}
