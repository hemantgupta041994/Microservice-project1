package com.example.address_service.service;

import com.example.address_service.entity.Address;
import com.example.address_service.repository.AddressRepository;
import com.example.address_service.request.CreateAddressRequest;
import com.example.address_service.response.AddressResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AddressServiceImpl implements AddressService {
    @Autowired
    AddressRepository addressRepository;

    /**
     * @param createAddressRequest
     * @return
     */
    @Override
    public AddressResponse createAddress(CreateAddressRequest createAddressRequest) {
        Address address = new Address();
        address.setStreet(createAddressRequest.getStreet());
        address.setCity(createAddressRequest.getCity());

        addressRepository.save(address);

        return new AddressResponse(address);
    }

    /**
     * @param id
     * @return
     */
    @Override
    public AddressResponse getById(long id) {
        Address address = addressRepository.findById(id).orElseGet(null);
        return new AddressResponse(address);
    }
}
