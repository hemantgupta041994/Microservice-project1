package com.example.student_service.feignclients;

import com.example.student_service.response.AddressResponse;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

//below is part of untill eureka-server not configured
//@FeignClient(url="${address.service.url}", value = "address-feign-client", path = "/api/address")
//following part once eureka server configured, request will be like from student-service -> address-service -> response to student-service
//@FeignClient(value = "address-service", path = "/api/address")
//following part after api gateway, here request will go from api gateway to address service
// request will be like from api-gateway -> student-service -> api-gateway -> address-service -> response to api-gateway -> response to student-service
@FeignClient(value = "api-gateway")
public interface AddressFeignClient {

    @GetMapping("/address-service/api/address/get-by-id/{id}")
    public AddressResponse getById(@PathVariable long id);
}
