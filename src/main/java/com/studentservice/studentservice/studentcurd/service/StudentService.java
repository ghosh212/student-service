package com.studentservice.studentservice.studentcurd.service;

import com.studentservice.studentservice.studentcurd.data.StudentData;
import com.studentservice.studentservice.studentcurd.data.StudentDataResponse;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Optional;

@Component
public interface StudentService {

    public StudentDataResponse addStudent(StudentData studentData);

    public StudentDataResponse deleteStudent(String registrationNumber);

    public StudentDataResponse updateStudent (String registrationNumber, StudentData studentData);

    public Optional<StudentData> getSingleStudent(String registrationNumber);

    public List<StudentData> getStudentByStandardAndSection(String section, Integer standard);

    public List<StudentData> getStudentByStandardAndSectionAndStream(String section,Integer standard, String plus12Stream);




}
