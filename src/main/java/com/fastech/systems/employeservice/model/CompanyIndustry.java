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
public class CompanyIndustry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer employeeID;
    @ManyToOne
    @JsonIgnore
    private Company company;
    @ManyToOne
    @JsonIgnore
    private Industry industry;



}
