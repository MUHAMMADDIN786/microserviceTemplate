package com.fastech.systems.employeservice.dto;

import com.fastech.systems.employeservice.model.Company;
import com.fastech.systems.employeservice.model.Industry;
import lombok.Getter;
import lombok.Setter;

import java.util.List;

@Getter
@Setter
public class CompanyIndustryDto {
    private Integer companyIndustryID;
    private Integer companyID;
    private Integer industryID;
    private List<Company> companyList;
    private List<Industry> industryList;

}
