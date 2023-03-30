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
public class SkillType {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer skillTypeID;
    private String skillTypeName;
    private String skillTypeDescription;
    @OneToMany(mappedBy = "skillType",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Skill> skillList=new ArrayList<>();



}
