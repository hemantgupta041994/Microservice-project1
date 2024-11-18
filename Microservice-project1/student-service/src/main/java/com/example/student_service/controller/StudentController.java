package com.example.student_service.controller;

import com.example.student_service.request.CreateStudentRequest;
import com.example.student_service.response.StudentResponse;
import com.example.student_service.service.StudentServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/api/student")
public class StudentController {

    @Autowired
    StudentServiceImpl studentService;

    @PostMapping("/create")
    public StudentResponse createStudent(@RequestBody CreateStudentRequest createStudentRequest){
        return studentService.createStudent(createStudentRequest);
    }

    @GetMapping("/get-by-id/{id}")
    public StudentResponse getById(@PathVariable long id){
        return studentService.getById(id);
    }
}
