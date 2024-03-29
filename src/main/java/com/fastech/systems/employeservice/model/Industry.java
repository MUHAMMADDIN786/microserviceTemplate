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
public class Industry {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer industryID;
    private String industryName;
    @Column(columnDefinition="text")
    private String industryDescription;
    @OneToMany(mappedBy = "industry",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<CompanyIndustry> companyIndustryList=new ArrayList<>();


}
