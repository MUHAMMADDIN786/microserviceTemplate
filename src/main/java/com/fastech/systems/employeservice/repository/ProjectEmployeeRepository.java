package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.ProjectEmployee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectEmployeeRepository extends JpaRepository<ProjectEmployee,Integer> {
    @Query("select p from ProjectEmployee p where p.employee.employeeID=?1")
    Page<ProjectEmployee> findByEmployeeID(Integer employeeID, Pageable pageable);

    @Query("select p from ProjectEmployee p where p.project.projectID=?1")
    Page<ProjectEmployee> findByProjectID(Integer employeeID, Pageable pageable);
}
