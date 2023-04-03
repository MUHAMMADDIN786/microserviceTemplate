package com.fastech.systems.employeservice.dto;

import lombok.Getter;
import lombok.Setter;

@Setter
@Getter
public class PaginationDto {
    Integer pageNo;
    Integer pageSize;
    Integer industryID;
    Integer companyID;
    Integer employeeID;
    Integer skillID;
    Integer projectID;
    Integer companyBranchID;
    Integer employementHistoryID;
}
