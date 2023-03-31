package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.CompanyDto;
import com.fastech.systems.employeservice.dto.IndustryDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.model.Company;
import com.fastech.systems.employeservice.model.Industry;
import com.fastech.systems.employeservice.repository.IndustryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class IndustryService {
    @Autowired
    private IndustryRepository repository;

    public Page<Industry> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public Industry create(IndustryDto dto) throws Exception {

        if(dto.getIndustryName().isBlank()){
            throw new Exception("Plese enter Valid Industry Name");
        }
        Industry entity= new Industry();
        entity.setIndustryName(dto.getIndustryName());
        entity.setIndustryDescription(dto.getIndustryDescription());
        return repository.save(entity);
    }
    public Industry findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("Industry not found for id = "+id));
    }
    public Industry update(IndustryDto dto) throws Exception {
        if(dto.getIndustryID()==null){
            throw new Exception("Enter Valid Id");
        }
        Industry entity= findById(dto.getIndustryID());
        if(!dto.getIndustryName().isBlank())
            entity.setIndustryName(dto.getIndustryName());
        if(!dto.getIndustryDescription().isBlank())
            entity.setIndustryDescription(dto.getIndustryDescription());

        return repository.save(entity);
    }
    public Industry removeUser(Integer id) throws Exception {
        Industry entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
