package com.fastech.systems.employeservice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.cloud.netflix.eureka.EnableEurekaClient;

@SpringBootApplication
@EnableEurekaClient
public class EmployeServiceApplication {

    public static void main(String[] args) {
        SpringApplication.run(EmployeServiceApplication.class, args);
    }

}
