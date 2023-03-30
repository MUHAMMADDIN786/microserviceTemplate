package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee,Integer> {
    @Query("select e from Employee e where e.email=?1")
    Employee findByEmail(String email);
}
