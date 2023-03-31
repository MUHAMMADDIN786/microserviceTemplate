package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.EmployeeDto;
import com.fastech.systems.employeservice.dto.EmployementHistoryDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.model.Company;
import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.EmployementHistory;
import com.fastech.systems.employeservice.model.Position;
import com.fastech.systems.employeservice.repository.EmployementHistoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

@Service
public class EmployementHistoryService {
    @Autowired
    private EmployementHistoryRepository repository;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private EmployeeService employeeService;
    @Autowired
    private PositionService positionService;

    public Page<EmployementHistory> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }

    public EmployementHistory create(EmployementHistoryDto dto) throws Exception {
        EmployementHistory entity = new EmployementHistory();

        if(dto.getCompanyID()==null){
            throw new Exception("Please Enter Valid companyId");
        }
        if(dto.getPositionID()==null){
            throw new Exception("Please Enter Valid positionId");
        }
        if(dto.getEmployeeID()==null){
            throw new Exception("Please Enter Valid employeeId");
        }
        Employee employee1= employeeService.findById(dto.getEmployeeID());
        Company company= companyService.findById(dto.getCompanyID());
        Position position= positionService.findById(dto.getPositionID());

        if(dto.getStartDate()==null){
            throw new Exception("mention Start Date");
        }
        if(dto.getStartDate()==null){
            throw new Exception("mention End Date");
        }
        entity.setCompany(company);
        entity.setEmployee(employee1);
        entity.setPosition(position);
        entity.setStartDate(dto.getStartDate());
        entity.setEndDate(dto.getEndDate());

        return repository.save(entity);
    }

    public EmployementHistory findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("EmployementHistory not found for id = "+id));
    }
    public EmployementHistory update(EmployementHistoryDto dto) throws Exception {

        if (dto.getEmployementHistoryID() == null)
            throw new Exception("Please mention employementHistory Id");
        EmployementHistory entity=repository.findById(dto.getEmployementHistoryID()).orElseThrow(()-> new Exception("EmployementHstory not found for id = "+dto.getEmployementHistoryID()));
        if(dto.getEmployeeID()!=null)
            entity.setEmployee(employeeService.findById(dto.getEmployeeID()));
        if(dto.getPositionID()!=null)
            entity.setPosition(positionService.findById(dto.getPositionID()));
        if(dto.getCompanyID()!=null)
            entity.setCompany(companyService.findById(dto.getCompanyID()));
        if(dto.getStartDate()!=null)
            entity.setStartDate(dto.getStartDate());
        if(dto.getEndDate()!=null)
            entity.setEndDate(dto.getEndDate());
        return repository.save(entity);
    }

    public EmployementHistory removeUser(Integer id) throws Exception {
        EmployementHistory findEmloyee = findById(id);
        repository.delete(findEmloyee);
        return findEmloyee;
    }

}
