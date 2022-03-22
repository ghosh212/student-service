package com.studentservice.studentservice.studentcurd.repo;

import com.studentservice.studentservice.studentcurd.data.StudentData;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.data.mongodb.repository.Query;

import java.util.List;

public interface StudentRepository extends MongoRepository<StudentData, String> {

    @Query("{'section':?0,'standard':?1}")
    List<StudentData> findAllStudentByStandardAndSection(String section, Integer standard);

    @Query("{'section':?0,'standard':?1,'plus12Stream':?2}")
    List<StudentData> findStudentByStandardAndSectionAndStream(String section, Integer standard, String plus12Stream);

    @Query("{'standard':?0}")
    List<StudentData> findStudentByStandard(Integer Standard);

}
