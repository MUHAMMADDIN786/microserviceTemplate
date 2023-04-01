package com.fastech.systems.employeservice.global;


import lombok.Getter;
import lombok.Setter;
import lombok.ToString;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.List;

@Slf4j
@Service
@Setter
@Getter
@ToString
public class APIResponse<T> {
    private String message;
    private Boolean hasError = false;
    private Integer totalCount;

    private Integer totalPages;
    private List<T> response;


}
