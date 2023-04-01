package com.fastech.systems.employeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Generated;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Entity
@Table
@Getter
@Setter
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeID;
    private String firstName;
    private String lastName;
    private String middleName;
    private String dob;
    private Integer ageCal;
    private String streetAddress;
    private String city;
    private String state;
    private String zip;
    private String countryCode;
    private String areaCode;
    private String phoneNumber;
    private String email;
    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SkillEmployee> skillEmployeeList=new ArrayList<>();

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProjectEmployee> projectEmployeeList=new ArrayList<>();

    @OneToMany(mappedBy = "employee",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EmployementHistory> employementHistoryList=new ArrayList<>();

}
