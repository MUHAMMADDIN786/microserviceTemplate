package com.fastech.systems.employeservice.dto;

import lombok.Getter;
import lombok.Setter;

import java.util.Date;
@Getter
@Setter
public class EmployeeDto {
    private Integer employeeID;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dob;
    private Double ageCal;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String areaCode;
    private String phoneNumber;
    private String email;
}
