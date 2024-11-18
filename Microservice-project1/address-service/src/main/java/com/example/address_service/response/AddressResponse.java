package com.example.address_service.response;

import com.example.address_service.entity.Address;
import lombok.Getter;

@Getter
public class AddressResponse {

    private long addressId;
    private String street;
    private String city;

    public AddressResponse(Address address){
        this.addressId = address.getId();
        this.street = address.getStreet();
        this.city = address.getCity();
    }
}
