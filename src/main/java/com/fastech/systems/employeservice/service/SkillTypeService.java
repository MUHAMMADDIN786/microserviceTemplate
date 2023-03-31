package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.EmployeeDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.SkillTypeDto;
import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.SkillType;
import com.fastech.systems.employeservice.repository.SkillTypeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SkillTypeService {
    @Autowired
    private SkillTypeRepository repository;

    public Page<SkillType> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }

    public SkillType create(SkillTypeDto dto) throws Exception {

        if(dto.getSkillTypeName().isBlank()){
            throw new Exception("Please Enter Valid skill type");
        }
        SkillType entity= new SkillType();
        entity.setSkillTypeName(dto.getSkillTypeName());
        entity.setSkillTypeDescription(dto.getSkillTypeDescription());

        return repository.save(entity);
    }

    public SkillType findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("SkillType not found for id = "+id));
    }
    public SkillType update(SkillTypeDto dto) throws Exception {

        if (dto.getSkillTypeID() == null)
            throw new Exception("Please mention skillType Id");
        SkillType entity=repository.findById(dto.getSkillTypeID()).orElseThrow(()-> new Exception("skillType not found for id = "+dto.getSkillTypeID()));

        if(!dto.getSkillTypeName().isBlank()){
            entity.setSkillTypeName(dto.getSkillTypeName());
        }
        if(!dto.getSkillTypeDescription().isBlank()){
            entity.setSkillTypeDescription(dto.getSkillTypeDescription());
        }
        return repository.save(entity);
    }

    public SkillType removeUser(Integer id) throws Exception {
        SkillType entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
