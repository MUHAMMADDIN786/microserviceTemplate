package com.fastech.systems.employeservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class EmployementHistoryDto {
    private String startDate;
    private String endDate;
    private Integer positionID;
    private Integer employeeID;
    private Integer companyID;
    private Integer employementHistoryID;
}
