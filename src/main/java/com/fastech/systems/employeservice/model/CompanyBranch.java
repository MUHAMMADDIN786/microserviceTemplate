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
public class CompanyBranch {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer companyBranchID;
    private String branchName;
    @Column(columnDefinition="text")
    private String branchDescription;
    private String streetAddress;
    private String state;
    private String city;
    private String zip;
    private String countryCode;
    private String phoneNumber;
    @OneToMany(mappedBy = "companyBranch",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Project> projectList=new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    private Company company;

}
