package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.CompanyIndustryDto;
import com.fastech.systems.employeservice.dto.IndustryDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.model.Company;
import com.fastech.systems.employeservice.model.CompanyIndustry;
import com.fastech.systems.employeservice.model.Industry;
import com.fastech.systems.employeservice.repository.CompanyIndustryRepository;
import com.fastech.systems.employeservice.repository.CompanyRepository;
import com.fastech.systems.employeservice.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class CompanyIndustryService {
    @Autowired
    private CompanyIndustryRepository repository;
    @Autowired
    private IndustryRepository industryRepository;
    @Autowired
    private CompanyRepository companyRepository;

    public Page<CompanyIndustry> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public Page<Company> findAllCompaniesByIndustryID(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        Page<CompanyIndustry> companyIndustryList= repository.findAllByIndustryID(dto.getIndustryID(),pageable);
    List<Company> companyList= new ArrayList<>();
    for(CompanyIndustry company:companyIndustryList){
        companyList.add(company.getCompany());
    }

        return new PageImpl<>(companyList, pageable, companyList.size());
    }
    public Page<Industry> findAllIndustriesByCompanyID(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        Page<CompanyIndustry> companyIndustryList= repository.findAllByCompanyID(dto.getCompanyID(),pageable);
        List<Industry> industryList= new ArrayList<>();
        for(CompanyIndustry industry:companyIndustryList){
            industryList.add(industry.getIndustry());
        }

        return new PageImpl<>(industryList, pageable, industryList.size());
    }
    private boolean checkIfAlreadyExists(Integer companyID, Integer industryID){
        return repository.existsByCompanyAndIndustry(companyID,industryID) != null;
    }
    public CompanyIndustry create(CompanyIndustryDto dto) throws Exception {

        if(dto.getCompanyID()==null){
            throw new Exception("Enter Valid CompanyID");
        }
        if(dto.getIndustryID()==null){
            throw new Exception("Enter Valid IndustryID");
        }
        if(checkIfAlreadyExists(dto.getCompanyID(),dto.getIndustryID())){
            throw new Exception("Record Already Exists with given company and industry IDs");
        }
        CompanyIndustry entity= new CompanyIndustry();
        entity.setIndustry(industryRepository.findById(dto.getIndustryID()).orElseThrow(()->new Exception("Industry not found")));
        entity.setCompany(companyRepository.findById(dto.getCompanyID()).orElseThrow(()->new Exception("Company not found")));
        return repository.save(entity);
    }
    public CompanyIndustry findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception(" not found for id = "+id));
    }
    public CompanyIndustry update(CompanyIndustryDto dto) throws Exception {
        if(dto.getCompanyIndustryID()==null){
            throw new Exception("Enter CompanyIndustry Id");
        }
        CompanyIndustry entity= findById(dto.getCompanyIndustryID());
        if(dto.getIndustryID()!=null)
            entity.setIndustry(industryRepository.findById(dto.getIndustryID()).orElseThrow(()->new Exception("Industry not found")));
        if(dto.getCompanyID()!=null)
            entity.setCompany(companyRepository.findById(dto.getCompanyID()).orElseThrow(()->new Exception("Company not found")));
        return repository.save(entity);
    }
    public CompanyIndustry removeUser(Integer id) throws Exception {
        CompanyIndustry entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
