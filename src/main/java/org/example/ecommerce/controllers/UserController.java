package org.example.ecommerce.controllers;

import java.util.List;

import org.example.ecommerce.database.models.Address;
import org.example.ecommerce.database.models.User;
import org.example.ecommerce.exception.NoAddressFoundException;
import org.example.ecommerce.service.address.AddressService;
import org.example.ecommerce.service.address.IAddressService;
import org.example.ecommerce.service.authentication.AuthService;
import org.example.ecommerce.service.authentication.IAuthService;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;



@RestController
@RequestMapping("/user/v1")
public class UserController {
    
    private IAddressService addressService;

    public UserController(AddressService addressService) {
        this.addressService = addressService;
    }

    @GetMapping("/{userId}/address")
    public ResponseEntity<List<Address>> getMethodName(@PathVariable Long userId) throws NoAddressFoundException {
        var address = addressService.getByUserId(userId);
        return new ResponseEntity<List<Address>>(address, HttpStatus.OK);
    }

    @PutMapping("/{userId}/address")
    public ResponseEntity<Address> putAddress(@PathVariable Long userId, @RequestBody Address address) {
        return new ResponseEntity<Address>(addressService.putAddress(userId, address), HttpStatus.ACCEPTED);
    }
    

    @PatchMapping("/{userId}/address")
    public ResponseEntity<Address> updateAddress(@PathVariable Long userId, @RequestBody Address address) throws NoAddressFoundException {
        var new_address = addressService.updateAddress(userId, address);
        return new ResponseEntity<Address>(new_address, HttpStatus.ACCEPTED);
    }
}
