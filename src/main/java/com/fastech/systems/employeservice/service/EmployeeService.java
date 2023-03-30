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
        Employee employee = new Employee();

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

        return employee;
    }

    public Employee findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("Employee not found for id = "+id));
    }
    public Employee update(EmployeeDto dto) throws Exception {

        if (dto.getEmployeeID() == null)
            throw new Exception("Please mention user Id");
        Employee employee=repository.findById(dto.getEmployeeID()).orElseThrow(()-> new Exception("Employee not found for id = "+dto.getEmployeeID()));

            return repository.save(employee);
        }

    public Employee removeUser(Integer id) throws Exception {
        Employee findEmloyee = findById(id);
        repository.delete(findEmloyee);
        return findEmloyee;
    }


}
