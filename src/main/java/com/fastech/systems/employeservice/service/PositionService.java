package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.CompanyIndustryDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.PositionDto;
import com.fastech.systems.employeservice.model.CompanyIndustry;
import com.fastech.systems.employeservice.model.Position;
import com.fastech.systems.employeservice.repository.PositionRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class PositionService {
    @Autowired
    private PositionRepository repository;


    public Page<Position> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public Position create(PositionDto dto) throws Exception {

        if(dto.getPositionName().isBlank()){
            throw new Exception("Enter Valid Position name");
        }

       Position entity= new Position();
        entity.setPositionName(dto.getPositionName());
        entity.setPositionDescription(dto.getPositionDescription());
        return repository.save(entity);
    }
    public Position findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("Position not found for id = "+id));
    }
    public Position update(PositionDto dto) throws Exception {
        if(dto.getPositionID()==null){
            throw new Exception("Enter Position Id");
        }
        Position entity= findById(dto.getPositionID());
       if(!dto.getPositionName().isBlank())
           entity.setPositionName(dto.getPositionName());
       if(!dto.getPositionDescription().isBlank())
           entity.setPositionDescription(dto.getPositionDescription());

        return repository.save(entity);
    }
    public Position removeUser(Integer id) throws Exception {
        Position entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
