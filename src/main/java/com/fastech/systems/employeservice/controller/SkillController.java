package com.fastech.systems.employeservice.controller;

import com.fastech.systems.employeservice.dto.EmployeeDto;
import com.fastech.systems.employeservice.dto.PaginationDto;
import com.fastech.systems.employeservice.dto.SkillDto;
import com.fastech.systems.employeservice.global.APIResponse;
import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.Skill;
import com.fastech.systems.employeservice.service.EmployeeService;
import com.fastech.systems.employeservice.service.SkillService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;
import java.util.List;

@RestController
@RequestMapping("skill/")
@Slf4j
public class SkillController {

    @Autowired
    private SkillService service;

    @GetMapping("getAll")
    public ResponseEntity<?> getAllData(@RequestBody PaginationDto paginationDto) {
        APIResponse<Skill> responseModel = new APIResponse<>();
        try {
            Page<Skill> list = service.findAll(paginationDto);
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
        APIResponse<Skill> responseModel = new APIResponse<>();
        try {
            List<Skill> list = Collections.singletonList(service.findById(id));
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
        APIResponse<Skill> responseModel = new APIResponse<>();
        try {
            List<Skill> list = Collections.singletonList(service.removeUser(id));
            responseModel.setResponse(list);
            responseModel.setTotalCount((int) list.size());
            responseModel.setTotalPages(1);
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return errorResponse(responseModel, e);
        }
    }
    @PostMapping("create")
    public ResponseEntity<?> addUser(@RequestBody SkillDto dto) {
        APIResponse<Skill> responseModel = new APIResponse<>();
        try {
            Skill saved = service.create(dto);
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
    public ResponseEntity<?> update(@RequestBody SkillDto dto) {
        APIResponse<Skill> responseModel = new APIResponse<>();
        try {
            List<Skill> list = Collections.singletonList(service.update(dto));
            responseModel.setResponse(list);
            responseModel.setTotalCount((int) list.size());
            responseModel.setTotalPages(1);
            return ResponseEntity.ok(responseModel);
        } catch (Exception e) {
            return errorResponse(responseModel, e);
        }
    }
    public static ResponseEntity<APIResponse<Skill>> errorResponse(APIResponse<Skill> responseModel, Exception e) {
        log.error(e.getMessage());
        responseModel.setTotalCount(0);
        responseModel.setTotalPages(0);
        responseModel.setMessage(e.getMessage());
        return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(responseModel);
    }
}
