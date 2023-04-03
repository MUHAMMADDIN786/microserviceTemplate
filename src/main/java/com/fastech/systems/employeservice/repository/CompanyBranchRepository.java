package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.CompanyBranch;
import com.fastech.systems.employeservice.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyBranchRepository extends JpaRepository<CompanyBranch,Integer> {
    @Query("select b from CompanyBranch b where b.company.companyID=?1")
    Page<CompanyBranch> findAllByCompanyID(Integer companyID, Pageable pageable);
}
