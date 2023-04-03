package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.CompanyDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.ProjectEmployeeDto;
import com.fastech.systems.employeservice.model.*;
import com.fastech.systems.employeservice.repository.CompanyRepository;
import com.fastech.systems.employeservice.repository.ProjectEmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ProjectEmloyeeService {
    @Autowired
    private ProjectEmployeeRepository repository;
    @Autowired
    private ProjectService projectService;
    @Autowired
    private EmployeeService employeeService;

    public Page<ProjectEmployee> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public Page<Project> findByEmployeeID(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        Page<ProjectEmployee> projectEmployees= repository.findByEmployeeID(dto.getEmployeeID(),pageable);
        List<Project> list= new ArrayList<>();
        for(ProjectEmployee entity:projectEmployees){
            list.add(entity.getProject());
        }

        return new PageImpl<>(list, pageable, list.size());
    }
    public Page<Employee> findByProjectID(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        Page<ProjectEmployee> projectEmployees= repository.findByProjectID(dto.getProjectID(),pageable);
        List<Employee> list= new ArrayList<>();
        for(ProjectEmployee entity:projectEmployees){
            list.add(entity.getEmployee());
        }

        return new PageImpl<>(list, pageable, list.size());
    }

    public ProjectEmployee create(ProjectEmployeeDto dto) throws Exception {

        if(dto.getProjectID() == null){
            throw new Exception("Plese enter Valid Project ID");
        }
        if(dto.getEmployeeID() == null){
            throw new Exception("Plese enter Valid Employee ID");
        }
        ProjectEmployee entity= new ProjectEmployee();
        entity.setProject(projectService.findById(dto.getProjectID()));
        entity.setEmployee(employeeService.findById(dto.getEmployeeID()));
        return repository.save(entity);
    }
    public ProjectEmployee findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception(" not found for id = "+id));
    }
    public ProjectEmployee update(ProjectEmployeeDto dto) throws Exception {
        if(dto.getProjectID()==null){
            throw new Exception("Enter Valid project Id");
        }
        if(dto.getEmployeeID()==null){
            throw new Exception("Enter Valid employee Id");
        }
        ProjectEmployee entity= findById(dto.getProjectEmployeeID());
        if(dto.getProjectID()!=null)
            entity.setProject(projectService.findById(dto.getProjectID()));
        if(dto.getEmployeeID()==null)
            entity.setEmployee(employeeService.findById(dto.getEmployeeID()));
        return repository.save(entity);
    }
    public ProjectEmployee removeUser(Integer id) throws Exception {
        ProjectEmployee entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
