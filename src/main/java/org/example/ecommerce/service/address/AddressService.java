package org.example.ecommerce.service.address;

import java.util.List;

import org.example.ecommerce.database.models.Address;
import org.example.ecommerce.database.models.User;
import org.example.ecommerce.database.repository.AddressRepository;
import org.example.ecommerce.database.repository.UserRepository;
import org.example.ecommerce.exception.NoAddressFoundException;
import org.springframework.stereotype.Service;


@Service
public class AddressService implements IAddressService{
    
    private AddressRepository addressRepository;
    private UserRepository userRepository;

    public AddressService(AddressRepository addressRepository, UserRepository userRepository) {
        this.addressRepository = addressRepository;
        this.userRepository = userRepository;
    }

    @Override
    public List<Address> getByUserId(Long userId) throws NoAddressFoundException {
        var address = addressRepository.findByUserId(userId);
        if(address.isEmpty()) {
            throw new NoAddressFoundException("No Addresses Found for this User");
        }

        return address;
    }

    @Override
    public Address putAddress(Long userId, Address address) {
        User user = new User();
        user.setId(userId);
        address.setUser(user);
        return addressRepository.save(address);
    }

    @Override
    public Address updateAddress(Long userId, Address address) throws NoAddressFoundException {
        var opAddress = addressRepository.findById(address.getId());
        if(opAddress.isPresent()) {
            var old_address = opAddress.get();
            if(old_address.getUser().getId() == userId) {
                address.setUser(old_address.getUser());
                return addressRepository.save(address);
            }
        }

        throw new NoAddressFoundException("No such address Found for user");
    }

}
