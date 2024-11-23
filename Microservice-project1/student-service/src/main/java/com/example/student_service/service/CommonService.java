package com.example.student_service.service;

import com.example.student_service.feignclients.AddressFeignClient;
import com.example.student_service.response.AddressResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CommonService {

    Logger logger = LoggerFactory.getLogger(CommonService.class);

    long count=1;

    @Autowired
    AddressFeignClient addressFeignClient;

    @CircuitBreaker(name="addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId){
        /*Mono<AddressResponse> addressResponseMono = webClient.get().uri("/get-by-id/"+addressId)
                .retrieve().bodyToMono(AddressResponse.class);
        return addressResponseMono.block();*/
        //above code is commented as no more using web client now using feignClient

        logger.info("count {}", count++);
        AddressResponse addressResponse = addressFeignClient.getById(addressId);
        return addressResponse;
    }

    public AddressResponse fallbackGetAddressById(long addressId, Throwable throwable){
        logger.error("error {}", throwable);
        return new AddressResponse();
    }
}
