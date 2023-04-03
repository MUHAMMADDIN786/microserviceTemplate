package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.SkillDto;
import com.fastech.systems.employeservice.dto.SkillEmployeeDto;
import com.fastech.systems.employeservice.model.*;
import com.fastech.systems.employeservice.repository.EmployeeRepository;
import com.fastech.systems.employeservice.repository.SkillEmployeeRepository;
import com.fastech.systems.employeservice.repository.SkillLevelRepository;
import com.fastech.systems.employeservice.repository.SkillRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class SkillEmployeeService {
    @Autowired
    private SkillEmployeeRepository repository;
    @Autowired
    private EmployeeRepository employeeRepository;
    @Autowired
    private SkillRepository skillRepository;
    @Autowired
    private SkillLevelRepository skillLevelRepository;


    public Page<SkillEmployee> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public Page<Skill> findSkillsByEmployeeID(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        Page<SkillEmployee> skillEmployees= repository.findSkillsByEmployeeID(dto.getEmployeeID(),pageable);
        List<Skill> skillList= new ArrayList<>();
        for(SkillEmployee entity:skillEmployees){
            skillList.add(entity.getSkill());
        }

        return new PageImpl<>(skillList, pageable, skillList.size());
    }
    public Page<Employee> findEmployeesBySkillID(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        Page<SkillEmployee> skillEmployees= repository.findEmployeesBySkillID(dto.getSkillID(),pageable);
        List<Employee> list= new ArrayList<>();
        for(SkillEmployee entity:skillEmployees){
            list.add(entity.getEmployee());
        }

        return new PageImpl<>(list, pageable, list.size());
    }
    public SkillEmployee create(SkillEmployeeDto dto) throws Exception {

        if(dto.getEmployeeID()==null){
            throw new Exception("Please Enter Valid EmployeeId");
        }
        if(dto.getSkillID()==null){
            throw new Exception("Please Enter Valid Skill Id");
        }
        if(dto.getSkillLevelID()==null){
            throw new Exception("Please Enter Valid skill level Id");
        }
        SkillEmployee entity= new SkillEmployee();
        entity.setEmployee(employeeRepository.findById(dto.getEmployeeID()).orElseThrow(()->new Exception("Employee not found")));
        entity.setSkill(skillRepository.findById(dto.getSkillID()).orElseThrow(()->new Exception("Skill not found")));
        entity.setSkillLevel(skillLevelRepository.findById(dto.getSkillLevelID()).orElseThrow(()->new Exception("Skill Level not found")));
        entity.setAchievedOn(dto.getAchievedOn());
        return repository.save(entity);
    }
    public SkillEmployee findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("SkillEmployee not found for id = "+id));
    }
    public SkillEmployee update(SkillEmployeeDto dto) throws Exception {

        if (dto.getSkillEmployeeID() == null)
            throw new Exception("Please mention skillEmployee Id");
        SkillEmployee entity=repository.findById(dto.getSkillEmployeeID()).orElseThrow(()-> new Exception("skillEmployee not found for id = "+dto.getSkillEmployeeID()));

        if(dto.getEmployeeID()!=null){
            entity.setEmployee(employeeRepository.findById(dto.getEmployeeID()).orElseThrow(()->new Exception("Employee not found")));
        }
        if(dto.getSkillID()!=null){
            entity.setSkill(skillRepository.findById(dto.getSkillID()).orElseThrow(()->new Exception("Skill not found")));
        }
        if(dto.getSkillLevelID()!=null){
            entity.setSkillLevel(skillLevelRepository.findById(dto.getSkillLevelID()).orElseThrow(()->new Exception("Skill Level not found")));
        }
        if(dto.getAchievedOn()!=null)
            entity.setAchievedOn(dto.getAchievedOn());
        return repository.save(entity);
    }
    public SkillEmployee removeUser(Integer id) throws Exception {
        SkillEmployee entity = findById(id);
        repository.delete(entity);
        return entity;
    }


}
