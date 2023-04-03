package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.SalaryHistory;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SalaryHistoryRepository extends JpaRepository<SalaryHistory,Integer> {
    @Query("select s from SalaryHistory s where s.employementHistory.employementHistoryID=?1")
    Page<SalaryHistory> findByEmployementHistoryID(Integer employementHistoryID, Pageable pageable);
}
