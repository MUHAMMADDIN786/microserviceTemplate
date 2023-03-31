package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.CompanyBranchDto;
import com.fastech.systems.employeservice.dto.CompanyDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.model.Company;
import com.fastech.systems.employeservice.model.CompanyBranch;
import com.fastech.systems.employeservice.repository.CompanyBranchRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class CompanyBranchService {
    @Autowired
    private CompanyBranchRepository repository;
    @Autowired
    private CompanyService companyService;

    public Page<CompanyBranch> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public CompanyBranch create(CompanyBranchDto dto) throws Exception {

        if(dto.getCompanyBranchID()==null) {
            throw new Exception("Plese enter Valid Company id");
        }
        if(dto.getBranchName().isBlank())
            throw new Exception("Enter branch name");
        if(dto.getCity().isBlank())
            throw new Exception("Enter city name");
        Company entity= new Company();
        entity.setCompanyName(dto.getCompanyName());
        entity.setCompanyDescription(dto.getCompanyDescription());
        return repository.save(entity);
    }
    public Company findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("Company not found for id = "+id));
    }
    public Company update(CompanyDto dto) throws Exception {
        if(dto.getCompanyID()==null){
            throw new Exception("Enter Valid Company Id");
        }
        Company entity= findById(dto.getCompanyID());
        if(!dto.getCompanyName().isBlank())
            entity.setCompanyName(dto.getCompanyName());
        if(!dto.getCompanyDescription().isBlank())
            entity.setCompanyDescription(dto.getCompanyDescription());

        return repository.save(entity);
    }
    public Company removeUser(Integer id) throws Exception {
        Company entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
