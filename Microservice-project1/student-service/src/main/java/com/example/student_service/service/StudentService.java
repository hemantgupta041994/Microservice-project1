package com.example.student_service.service;


import com.example.student_service.request.CreateStudentRequest;
import com.example.student_service.response.StudentResponse;

interface StudentService {
    StudentResponse createStudent(CreateStudentRequest createStudentRequest);

    StudentResponse getById(long id);
}
