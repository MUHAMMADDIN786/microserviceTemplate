package com.fastech.systems.employeservice.service;

import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.TaskDto;
import com.fastech.systems.employeservice.model.ProjectEmployee;
import com.fastech.systems.employeservice.model.Task;
import com.fastech.systems.employeservice.repository.TaskRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageImpl;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class TaskService {
    @Autowired
    private TaskRepository repository;
    @Autowired
    private ProjectEmloyeeService projectEmloyeeService;


    public Page<Task> findAll(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findAll(pageable);
    }
    public Page<Task> findTasksByProjectID(PaginationDto dto) {
        Pageable pageable = PageRequest.of(dto.getPageNo(), dto.getPageSize());
        return repository.findTasksByProjectID(dto.getProjectID(),pageable);
    }

    public Task create(TaskDto dto) throws Exception {

        if(dto.getTaskName().isBlank()){
            throw new Exception("Plese task Name");
        }

        if(dto.getProjectEmployeeID()==null){
            throw new Exception("enter project employee id");
        }
        Task entity= new Task();
        entity.setTaskName(dto.getTaskName());
        entity.setTaskDescription(dto.getTaskDescription());
        entity.setProjectEmployee(projectEmloyeeService.findById(dto.getProjectEmployeeID()));
        return repository.save(entity);
    }
    public Task findById(Integer id) throws Exception {
        return repository.findById(id).orElseThrow(()-> new Exception("not found for id = "+id));
    }
    public Task update(TaskDto dto) throws Exception {
        if(dto.getTaskID()==null){
            throw new Exception("Enter Valid task Id");
        }
        Task entity= findById(dto.getTaskID());
        if(!dto.getTaskName().isBlank())
            entity.setTaskName(dto.getTaskName());
        if(!dto.getTaskDescription().isBlank())
            entity.setTaskDescription(dto.getTaskDescription());
        if(dto.getProjectEmployeeID()!=null)
            entity.setProjectEmployee(projectEmloyeeService.findById(dto.getProjectEmployeeID()));

        return repository.save(entity);
    }
    public Task removeUser(Integer id) throws Exception {
        Task entity = findById(id);
        repository.delete(entity);
        return entity;
    }

}
