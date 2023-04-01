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
public class SkillLevel {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer skillLevelID;
    private Integer skillLevelInt;
    private String skillLevelName;
    @Column(columnDefinition="text")
    private String skillLevelDescription;
    @OneToMany(mappedBy = "skillLevel",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SkillEmployee> skillEmployeeList=new ArrayList<>();

}
