package com.fastech.systems.employeservice.dto;

import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.Skill;
import com.fastech.systems.employeservice.model.SkillLevel;
import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.ManyToOne;

@Getter
@Setter
public class SkillEmployeeDto {
    private Integer skillEmployeeID;
    private String achievedOn;

    private Integer employeeID;
    private Integer skillID;
    private Integer skillLevelID;
}
