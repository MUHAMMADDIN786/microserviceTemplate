package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.CompanyBranch;
import com.fastech.systems.employeservice.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyBranchRepository extends JpaRepository<CompanyBranch,Integer> {
}
