package com.fastech.systems.employeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.Getter;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

@Entity
@Table
@Getter
@Setter
public class SalaryHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer salaryID;
    private String startDate;
    private String endDate;
    private String amount;
    @ManyToOne
    @JsonIgnore
    private EmployementHistory employementHistory;

}
