package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.EmployeeDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.Period;
import java.time.format.DateTimeFormatter;
import java.util.Date;


@Service
public class EmployeeService {
    @Autowired
    private EmployeeRepository repository;
    public Employee getByEmail(String email) {
        return repository.findByEmail(email);
    }

    private boolean checkEmailIfAlreadyExists(String email) {
        return repository.findByEmail(email) != null;
    }

    public Page<Employee> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }

    public Employee create(EmployeeDto dto) throws Exception {
        Employee entity = new Employee();
        if(dto.getEmail().isBlank()){
            throw new Exception("Please Enter Valid Email");
        }
        if (checkEmailIfAlreadyExists(dto.getEmail())) {
            throw new Exception("Email " + dto.getEmail() + "User Already Exists");
        }
        if(dto.getFirstName().isBlank()){
            throw new Exception("Please Enter Valid firstName");
        }
        if(dto.getLastName().isBlank()){
            throw new Exception("Please Enter Valid lastName");
        }
        if(dto.getPhoneNumber().isBlank()){
            throw new Exception("Please Enter Valid phoneNumber");
        }
        entity.setFirstName(dto.getFirstName());
        entity.setLastName(dto.getLastName());
        entity.setEmail(dto.getEmail());
        entity.setPhoneNumber(dto.getPhoneNumber());
        entity.setMiddleName(dto.getMiddleName());
        entity.setCity(dto.getCity());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate dob = LocalDate.parse(dto.getDob());
        entity.setDob(dtf.format(dob));
        LocalDate curDate = LocalDate.now();
        entity.setAgeCal(Period.between(dob, curDate).getYears());
        entity.setAreaCode(dto.getAreaCode());
        entity.setCountryCode(dto.getCountryCode());
        entity.setState(dto.getState());
        entity.setZip(dto.getZip());
        entity.setStreetAddress(dto.getStreetAddress());

        return repository.save(entity);
    }

    public Employee findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("Employee not found for id = "+id));
    }
    public Employee update(EmployeeDto dto) throws Exception {

        if (dto.getEmployeeID() == null)
            throw new Exception("Please mention user Id");
        Employee enity=findById(dto.getEmployeeID());
        if(!dto.getFirstName().isBlank())
            enity.setFirstName(dto.getFirstName());
        if(!dto.getLastName().isBlank())
            enity.setLastName(dto.getLastName());
        if(!dto.getEmail().isBlank())
            enity.setEmail(dto.getEmail());
        if(!dto.getPhoneNumber().isBlank())
            enity.setPhoneNumber(dto.getPhoneNumber());
        if(!dto.getMiddleName().isBlank())
            enity.setMiddleName(dto.getMiddleName());
        if(!dto.getCity().isBlank())
            enity.setCity(dto.getCity());
        DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
        LocalDate curDate = LocalDate.now();
        LocalDate dob = LocalDate.parse(dto.getDob());
        if(!dto.getDob().isBlank())
            enity.setDob(dtf.format(dob));
            enity.setAgeCal(Period.between(dob, curDate).getYears());
        if(!dto.getAreaCode().isBlank())
            enity.setAreaCode(dto.getAreaCode());
        if(!dto.getCountryCode().isBlank())
            enity.setCountryCode(dto.getCountryCode());
        if(!dto.getState().isBlank())
            enity.setState(dto.getState());
        if(!dto.getZip().isBlank())
            enity.setZip(dto.getZip());
        if(!dto.getStreetAddress().isBlank())
            enity.setStreetAddress(dto.getStreetAddress());

            return repository.save(enity);
        }

    public Employee removeUser(Integer id) throws Exception {
        Employee findEmloyee = findById(id);
        repository.delete(findEmloyee);
        return findEmloyee;
    }


}
