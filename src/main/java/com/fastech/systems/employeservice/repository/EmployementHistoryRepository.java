package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.EmployementHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployementHistoryRepository extends JpaRepository<EmployementHistory,Integer> {
    @Query("select e from EmployementHistory  e where e.employee.employeeID=?1")
    Page<EmployementHistory> findByEmployeeID(Integer employeeID, Pageable pageable);

    @Query("select e from EmployementHistory  e where e.company.companyID=?1")
    Page<EmployementHistory> findByCompanyID(Integer companyID, Pageable pageable);
}
