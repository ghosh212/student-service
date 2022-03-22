package com.studentservice.studentservice.studentcurd.data;

import lombok.*;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class Address {

    @NonNull
    private int houseNumber;
    private String lane;
    private String landmark;
    @NonNull
    private String textField1;
    private String textField2;
    @NonNull
    private int pinCode;
    @NonNull
    private String city;
    @NonNull
    private String state;
    private String Country;

}
