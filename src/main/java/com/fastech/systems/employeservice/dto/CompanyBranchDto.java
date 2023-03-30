package com.fastech.systems.employeservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class CompanyBranchDto {
    private Integer companyBranchID;
    private String branchName;
    private String branchDescription;
    private String streetAddress;
    private String state;
    private String city;
    private String zip;
    private String countryCode;
    private String phoneNumber;
}
