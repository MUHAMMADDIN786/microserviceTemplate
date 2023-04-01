package com.fastech.systems.employeservice.dto;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class TaskDto {
    private Integer taskID;
    private Integer projectEmployeeID;
    private String taskName;
    private String taskDescription;
}
