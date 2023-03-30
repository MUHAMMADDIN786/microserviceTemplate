package com.fastech.systems.employeservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class SalaryHistoryDto {
    private Integer salaryID;
    private String startDate;
    private String endDate;
    private String amount;
}
