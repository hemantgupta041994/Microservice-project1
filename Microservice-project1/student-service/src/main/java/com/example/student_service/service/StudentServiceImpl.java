package com.example.student_service.service;


import com.example.student_service.entity.Student;
import com.example.student_service.feignclients.AddressFeignClient;
import com.example.student_service.repository.StudentRepository;
import com.example.student_service.request.CreateStudentRequest;
import com.example.student_service.response.AddressResponse;
import com.example.student_service.response.StudentResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
//import org.springframework.web.reactive.function.client.WebClient;
//import reactor.core.publisher.Mono;

@Service
public class StudentServiceImpl implements StudentService {
    @Autowired
    StudentRepository studentRepository;

    @Autowired
    AddressFeignClient addressFeignClient;

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
        studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));

        return studentResponse;
    }

    /**
     * @param id
     * @return
     */
    @Override
    public StudentResponse getById(long id) {
        Student student = studentRepository.findById(id).orElseGet(null);
        StudentResponse studentResponse = new StudentResponse(student);
//        studentResponse.setAddressResponse(getAddressById(student.getAddressId()));
        studentResponse.setAddressResponse(addressFeignClient.getById(student.getAddressId()));
        return studentResponse;
    }

    /*public AddressResponse getAddressById(long addressId){
        Mono<AddressResponse> addressResponseMono = webClient.get().uri("/get-by-id/"+addressId)
                .retrieve().bodyToMono(AddressResponse.class);
        return addressResponseMono.block();
    }*/
}
