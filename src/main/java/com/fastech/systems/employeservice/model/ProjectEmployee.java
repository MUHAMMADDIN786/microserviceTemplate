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
public class ProjectEmployee {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer projectEmployeeID;
    @OneToMany(mappedBy = "projectEmployee",cascade = CascadeType.ALL,orphanRemoval = true,fetch = FetchType.LAZY)
    @JsonIgnore
    private List<Task> taskList=new ArrayList<>();
    @ManyToOne
    @JsonIgnore
    private Project project;
    @ManyToOne
    @JsonIgnore
    private Employee employee;

}
