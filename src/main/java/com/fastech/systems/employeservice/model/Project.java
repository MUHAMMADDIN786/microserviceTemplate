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
public class Project {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer projectID;
    private String projectName;
    @Column(columnDefinition="text")
    private String projectDescription;
    private String startDate;
    private String endDate;
    private String budget;
    @OneToMany(mappedBy = "project",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<ProjectEmployee> projectEmployeeList=new ArrayList<>();

    @ManyToOne
    @JsonIgnore
    private CompanyBranch companyBranch;
}
