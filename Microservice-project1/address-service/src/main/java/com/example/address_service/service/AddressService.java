package com.example.address_service.service;

import com.example.address_service.request.CreateAddressRequest;
import com.example.address_service.response.AddressResponse;

interface AddressService {
    AddressResponse createAddress(CreateAddressRequest createAddressRequest);

    AddressResponse getById(long id);
}
