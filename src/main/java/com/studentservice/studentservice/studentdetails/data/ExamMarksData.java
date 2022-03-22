package com.studentservice.studentservice.studentdetails.data;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.Map;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class ExamMarksData {

    //full marks from zookeeper/external config to-do
    private Map<String,Integer> unitTest_1;
    private Map<String,Integer> midYearTerm;
    private Map<String,Integer> unitTest_2;
    private Map<String,Integer> finalTerm;
    private Map<String,Integer> totalMarks;
    private Integer rank;
    private boolean rankHolder;
    private Float percentage;

}
