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
public class SkillEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer skillEmployeeID;
   private String achievedOn;
   @ManyToOne
    @JsonIgnore
    private Employee employee;
    @ManyToOne
    @JsonIgnore
    private Skill skill;
    @ManyToOne
    @JsonIgnore
    private SkillLevel skillLevel;
}
