package com.example.student_service.feignclients;

import com.example.student_service.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//below is part of untill eureka-server not configured
//@FeignClient(url="${address.service.url}", value = "address-feign-client", path = "/api/address")
@FeignClient(value = "address-service", path = "/api/address")
public interface AddressFeignClient {

    @GetMapping("/get-by-id/{id}")
    public AddressResponse getById(@PathVariable long id);
}
