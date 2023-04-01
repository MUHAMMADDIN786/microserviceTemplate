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
public class Task {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Integer taskID;
    private String taskName;
    @Column(columnDefinition="text")
    private String taskDescription;
    @ManyToOne
    @JsonIgnore
    private ProjectEmployee projectEmployee;

}
