package com.example.student_service.service;


import com.example.student_service.entity.Student;
import com.example.student_service.feignclients.AddressFeignClient;
import com.example.student_service.repository.StudentRepository;
import com.example.student_service.request.CreateStudentRequest;
import com.example.student_service.response.AddressResponse;
import com.example.student_service.response.StudentResponse;
import io.github.resilience4j.circuitbreaker.annotation.CircuitBreaker;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {
    Logger logger = LoggerFactory.getLogger(StudentServiceImpl.class);
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressFeignClient addressFeignClient;

    @Autowired
    CommonService commonService;

    /*@Autowired
    WebClient webClient;*/
    /**
     * @param createStudentRequest
     * @return
     */
    @Override
    public StudentResponse createStudent(CreateStudentRequest createStudentRequest) {
        Student student = new Student();
        student.setFirstName(createStudentRequest.getFirstName());
        student.setLastName(createStudentRequest.getLastName());
        student.setEmail(createStudentRequest.getEmail());
        student.setAddressId(createStudentRequest.getAddressId());

        student = studentRepository.save(student);

        StudentResponse studentResponse = new StudentResponse(student);
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));

        return studentResponse;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public StudentResponse getById(long id) {
        logger.info("Inside student getById");
        Student student = studentRepository.findById(id).orElseGet(null);
        StudentResponse studentResponse = new StudentResponse(student);
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        studentResponse.setAddressResponse(commonService.getAddressById(student.getAddressId()));
        return studentResponse;
    }

    //commented below code moved to commonService, to implement circuit-breaker.
//    as circuit-breaker internally use spring-aop and AOP will not work if circuit-breaker method and fallback method are in same class.
//    to avoid this issue following two method's move to commonService
    /*@CircuitBreaker(name="addressService", fallbackMethod = "fallbackGetAddressById")
    public AddressResponse getAddressById(long addressId){
        *//*Mono<AddressResponse> addressResponseMono = webClient.get().uri("/get-by-id/"+addressId)
                .retrieve().bodyToMono(AddressResponse.class);
        return addressResponseMono.block();*//*
        //above code is commented as no more using web client now using feignClient

        AddressResponse addressResponse = addressFeignClient.getById(addressId);
        return addressResponse;
    }

    public AddressResponse fallbackGetAddressById(long addressId, Throwable throwable){
        return new AddressResponse();
    }*/
}
