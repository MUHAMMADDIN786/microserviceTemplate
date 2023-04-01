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
public class Company {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer companyID;
    private String companyName;
    @Column(columnDefinition="text")
    private String companyDescription;
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CompanyBranch> companyBranchList=new ArrayList<>();
    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CompanyIndustry> companyIndustryList=new ArrayList<>();

    @OneToMany(mappedBy = "company",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<EmployementHistory> employementHistoryList=new ArrayList<>();

}
