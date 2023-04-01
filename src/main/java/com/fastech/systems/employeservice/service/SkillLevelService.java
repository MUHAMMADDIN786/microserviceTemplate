package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.CompanyDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.SkillLevelDto;
import com.fastech.systems.employeservice.model.Company;
import com.fastech.systems.employeservice.model.SkillLevel;
import com.fastech.systems.employeservice.repository.CompanyRepository;
import com.fastech.systems.employeservice.repository.SkillLevelRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SkillLevelService {
    @Autowired
    private SkillLevelRepository repository;

    public Page<SkillLevel> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public SkillLevel create(SkillLevelDto dto) throws Exception {

        if(dto.getSkillLevelName().isBlank()){
            throw new Exception("Plese enter skill level Name");
        }
        SkillLevel entity= new SkillLevel();
        entity.setSkillLevelName(dto.getSkillLevelName());
        entity.setSkillLevelDescription(dto.getSkillLevelDescription());
        entity.setSkillLevelInt(dto.getSkillLevelInt());
        return repository.save(entity);
    }
    public SkillLevel findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception(" not found for id = "+id));
    }
    public SkillLevel update(SkillLevelDto dto) throws Exception {
        if(dto.getSkillLevelID()==null){
            throw new Exception("Enter Valid  Id");
        }
        SkillLevel entity= findById(dto.getSkillLevelID());
        if(!dto.getSkillLevelName().isBlank())
            entity.setSkillLevelName(dto.getSkillLevelName());
        if(!dto.getSkillLevelDescription().isBlank())
            entity.setSkillLevelDescription(dto.getSkillLevelDescription());
        if(dto.getSkillLevelInt()!=null)
            entity.setSkillLevelInt(dto.getSkillLevelInt());

        return repository.save(entity);
    }
    public SkillLevel removeUser(Integer id) throws Exception {
        SkillLevel entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
