package com.studentservice.studentservice.studentdetails.data;

import lombok.*;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document("studentdetails")
public class StudentDetails {

    @NonNull
    private String registrationNumber;
    private Integer rollNumber;
    private ExamMarksData examMarksData;
    private Subject subjects;



}
