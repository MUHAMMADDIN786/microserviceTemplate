package com.fastech.systems.employeservice.controller;

import com.fastech.systems.employeservice.dto.EmployeeDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.SkillLevelDto;
import com.fastech.systems.employeservice.global.APIResponse;
import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.Skill;
import com.fastech.systems.employeservice.model.SkillLevel;
import com.fastech.systems.employeservice.service.EmployeeService;
import com.fastech.systems.employeservice.service.SkillLevelService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("skillLevel/")
@Slf4j
public class SkillLevelController {

    @Autowired
    private SkillLevelService service;

    @GetMapping("getAll")
    public ResponseEntity<?> getAllData(@RequestBody PaginationDto paginationDto) {
        APIResponse<SkillLevel> responseModel = new APIResponse<>();
        try {
            Page<SkillLevel> list = service.findAll(paginationDto);
            responseModel.setResponse(list.getContent());
            responseModel.setTotalCount((int) list.getTotalElements());
            responseModel.setTotalPages(list.getTotalPages());
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return errorResponse(responseModel, e);
        }
    }
    @GetMapping("findById")
    public ResponseEntity<?> findById(@RequestParam(name="id") Integer id) {
        APIResponse<SkillLevel> responseModel = new APIResponse<>();
        try {
            List<SkillLevel> list = Collections.singletonList(service.findById(id));
            responseModel.setResponse(list);
            responseModel.setTotalCount((int) list.size());
            responseModel.setTotalPages(1);
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return errorResponse(responseModel, e);
        }
    }
    @DeleteMapping("delete")
    public ResponseEntity<?> delete(@RequestParam(name="id") Integer id) {
        APIResponse<SkillLevel> responseModel = new APIResponse<>();
        try {
            List<SkillLevel> list = Collections.singletonList(service.removeUser(id));
            responseModel.setResponse(list);
            responseModel.setTotalCount((int) list.size());
            responseModel.setTotalPages(1);
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return errorResponse(responseModel, e);
        }
    }
    @PostMapping("create")
    public ResponseEntity<?> addUser(@RequestBody SkillLevelDto dto) {
        APIResponse<SkillLevel> responseModel = new APIResponse<>();
        try {
            SkillLevel saved = service.create(dto);
            responseModel.setResponse(Collections.singletonList(saved));
            responseModel.setTotalCount(1);
            responseModel.setMessage("record created successfully");
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            log.error(e.getMessage());
            responseModel.setMessage(e.getMessage());
            return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
        }
    }
    @PutMapping("update")
    public ResponseEntity<?> update(@RequestBody SkillLevelDto dto) {
        APIResponse<SkillLevel> responseModel = new APIResponse<>();
        try {
            List<SkillLevel> list = Collections.singletonList(service.update(dto));
            responseModel.setResponse(list);
            responseModel.setTotalCount((int) list.size());
            responseModel.setTotalPages(1);
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return errorResponse(responseModel, e);
        }
    }
    public static ResponseEntity<APIResponse<SkillLevel>> errorResponse(APIResponse<SkillLevel> responseModel, Exception e) {
        log.error(e.getMessage());
        responseModel.setTotalCount(0);
        responseModel.setTotalPages(0);
        responseModel.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
    }
}
