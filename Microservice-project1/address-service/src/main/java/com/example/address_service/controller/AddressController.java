package com.example.address_service.controller;

import com.example.address_service.request.CreateAddressRequest;
import com.example.address_service.response.AddressResponse;
import com.example.address_service.service.AddressServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/address")
public class AddressController {

    @Autowired
    AddressServiceImpl addressService;

    @PostMapping("/create")
    public AddressResponse createAddress(@RequestBody CreateAddressRequest createAddressRequest){
        return addressService.createAddress(createAddressRequest);
    }

    @GetMapping("/get-by-id/{id}")
    public AddressResponse getById(@PathVariable long id){
        return addressService.getById(id);
    }
}
