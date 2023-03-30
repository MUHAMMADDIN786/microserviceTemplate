package com.fastech.systems.employeservice.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
public class EmployementHistory {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employementHistoryID;
    private String startDate;
    private String endDate;
    @ManyToOne
    @JsonIgnore
    private Company company;
    @ManyToOne
    @JsonIgnore
    private Position position;
    @ManyToOne
    @JsonIgnore
    private Employee employee;
    @OneToMany(mappedBy = "employementHistory",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SalaryHistory> salaryHistoryList=new ArrayList<>();


}
