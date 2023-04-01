package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.ProjectDto;
import com.fastech.systems.employeservice.model.Company;
import com.fastech.systems.employeservice.model.Project;
import com.fastech.systems.employeservice.repository.ProjectRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.time.LocalDate;
import java.time.format.DateTimeFormatter;

@Service
public class ProjectService {
    @Autowired
    private ProjectRepository repository;
    @Autowired
    private CompanyBranchService companyBranchService;

    public Page<Project> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public Project create(ProjectDto dto) throws Exception {

        if(dto.getCompanyBranchID()==null){
            throw new Exception("Plese enter Valid CompanyBranch ID");
        }
        if(dto.getProjectName().isBlank()){
            throw new Exception("Plese enter project Name");
        }
        if(dto.getProjectName().isBlank()){
            throw new Exception("Plese enter project Budget");
        }
        if(dto.getEndDate()==null || dto.getStartDate()==null){
            throw new Exception("Plese enter project Start/ End Date");
        }
        Project entity= new Project();
        entity.setProjectName(dto.getProjectName());
        entity.setProjectDescription(dto.getProjectDescription());
        entity.setBudget(dto.getBudget());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dto.getStartDate());
        LocalDate endDate = LocalDate.parse(dto.getEndDate());
        LocalDate curDate = LocalDate.now();
        entity.setStartDate(dtf.format(startDate));
        entity.setEndDate(dtf.format(endDate));
        entity.setCompanyBranch(companyBranchService.findById(dto.getCompanyBranchID()));

        return repository.save(entity);
    }
    public Project findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("Project not found for id = "+id));
    }
    public Project update(ProjectDto dto) throws Exception {
        if(dto.getProjectID()==null){
            throw new Exception("Enter Valid Project Id");
        }
        Project entity= findById(dto.getProjectID());
        if(!dto.getProjectName().isBlank())
            entity.setProjectName(dto.getProjectName());
        if(!dto.getProjectDescription().isBlank())
            entity.setProjectDescription(dto.getProjectDescription());
        if(dto.getCompanyBranchID()!=null)
            entity.setCompanyBranch(companyBranchService.findById(dto.getCompanyBranchID()));
        if(!dto.getBudget().isBlank())
            entity.setBudget(dto.getBudget());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate startDate = LocalDate.parse(dto.getStartDate());
        LocalDate endDate = LocalDate.parse(dto.getEndDate());
        LocalDate curDate = LocalDate.now();
        if(!dto.getStartDate().isBlank())
            entity.setStartDate(dtf.format(startDate));
        if(!dto.getEndDate().isBlank())
            entity.setEndDate(dtf.format(endDate));

        return repository.save(entity);
    }
    public Project removeUser(Integer id) throws Exception {
        Project entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
