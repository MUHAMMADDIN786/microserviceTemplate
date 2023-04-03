package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.PositionDto;
import com.fastech.systems.employeservice.dto.SalaryHistoryDto;
import com.fastech.systems.employeservice.model.Position;
import com.fastech.systems.employeservice.model.SalaryHistory;
import com.fastech.systems.employeservice.repository.SalaryHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class SalaryHistoryService {
    @Autowired
    private SalaryHistoryRepository repository;
    @Autowired
    private EmployementHistoryService employementHistoryService;


    public Page<SalaryHistory> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public Page<SalaryHistory> findByEmployementHistoryID(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findByEmployementHistoryID(dto.getEmployementHistoryID(),pageable);
    }
    public SalaryHistory create(SalaryHistoryDto dto) throws Exception {

        if(dto.getEmployementHistoryID()==null){
            throw new Exception("Enter Valid EmployementHistory Id");
        }
        if(dto.getStartDate()==null)
            throw new Exception("Enter Valid Start Date");
        if(dto.getEndDate()==null)
            throw new Exception("Enter Valid End Date");
        if(dto.getAmount().isBlank())
            throw new Exception("Enter Valid Amount");

        SalaryHistory entity= new SalaryHistory();
        entity.setEmployementHistory(employementHistoryService.findById(dto.getEmployementHistoryID()));
        entity.setAmount(dto.getAmount());
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());
        return repository.save(entity);
    }
    public SalaryHistory findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("SalaryHistory not found for id = "+id));
    }
    public SalaryHistory update(SalaryHistoryDto dto) throws Exception {
        if(dto.getSalaryID()==null){
            throw new Exception("Enter SalaryHistory Id");
        }
        SalaryHistory entity= findById(dto.getSalaryID());
        if(!dto.getAmount().isBlank())
            entity.setAmount(dto.getAmount());
        if(dto.getStartDate()==null)
            entity.setStartDate(dto.getStartDate());
        if(dto.getEndDate()==null)
            entity.setEndDate(dto.getEndDate());
        if(dto.getEmployementHistoryID()!=null){
            entity.setEmployementHistory(employementHistoryService.findById(dto.getEmployementHistoryID()));        }

        return repository.save(entity);
    }
    public SalaryHistory  removeUser(Integer id) throws Exception {
        SalaryHistory entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
