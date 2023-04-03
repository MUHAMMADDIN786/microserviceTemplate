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

    public Page<CompanyBranch> findAllByCompanyID(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAllByCompanyID(dto.getCompanyID(),pageable);
    }
    public CompanyBranch create(CompanyBranchDto dto) throws Exception {

        if(dto.getCompanyID()==null) {
            throw new Exception("Plese enter Valid Company id");
        }
        if(dto.getBranchName().isBlank())
            throw new Exception("Enter branch name");
        if(dto.getCity().isBlank())
            throw new Exception("Enter city name");
        if(dto.getPhoneNumber().isBlank())
            throw new Exception("Enter city name");
        if(dto.getStreetAddress().isBlank())
            throw new Exception("Enter city name");
        CompanyBranch entity= new CompanyBranch();
        entity.setBranchName(dto.getBranchName());
        entity.setCompany(companyService.findById(dto.getCompanyID()));
        entity.setCity(dto.getCity());
        entity.setBranchDescription(dto.getBranchDescription());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setZip(dto.getZip());
        entity.setState(dto.getState());
        entity.setCountryCode(dto.getCountryCode());
        entity.setStreetAddress(dto.getStreetAddress());
        return repository.save(entity);
    }
    public CompanyBranch findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("CompanyBranch not found for id = "+id));
    }
    public CompanyBranch update(CompanyBranchDto dto) throws Exception {
        if(dto.getCompanyBranchID()==null){
            throw new Exception("Enter Valid CompanyBranch Id");
        }
        CompanyBranch entity= findById(dto.getCompanyBranchID());
        if(!dto.getBranchName().isBlank())
            entity.setBranchName(dto.getBranchName());
        if(!dto.getBranchDescription().isBlank())
            entity.setBranchDescription(dto.getBranchDescription());
        if(dto.getCompanyID()!=null)
            entity.setCompany(companyService.findById(dto.getCompanyID()));
        if(!dto.getCity().isBlank())
            entity.setCity(dto.getCity());
        if(!dto.getZip().isBlank())
            entity.setZip(dto.getZip());
        if(!dto.getState().isBlank())
            entity.setState(dto.getState());
        if(!dto.getCountryCode().isBlank())
            entity.setCountryCode(dto.getCountryCode());
        if(!dto.getStreetAddress().isBlank())
            entity.setStreetAddress(dto.getStreetAddress());
        return repository.save(entity);
    }
    public CompanyBranch removeUser(Integer id) throws Exception {
        CompanyBranch entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
