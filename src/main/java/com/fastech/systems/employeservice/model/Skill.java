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
public class Skill {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer skillID;
    private String skillName;
    private String skillDescription;
    @OneToMany(mappedBy = "skill",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<SkillEmployee> skillEmployeeList=new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    private SkillType skillType;

}
