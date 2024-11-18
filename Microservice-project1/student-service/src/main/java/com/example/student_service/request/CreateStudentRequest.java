package com.example.student_service.request;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@AllArgsConstructor
@NoArgsConstructor
@Data
public class CreateStudentRequest {
    private String firstName;
    private String lastName;
    private String email;
    private long addressId;
}
