package com.studentservice.studentservice.studentdetails.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Subject {

    private String plus12Stream;
    private List<String> subjects;
    private List<String> specialSubjects;

}
