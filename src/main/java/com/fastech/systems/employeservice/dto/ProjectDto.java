package com.fastech.systems.employeservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ProjectDto {
    private Integer projectID;
    private String projectName;
    private String projectDescription;
    private String startDate;
    private String endDate;
    private String budget;
    private Integer companyBranchID;
}
