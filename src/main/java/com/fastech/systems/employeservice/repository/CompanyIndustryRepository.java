package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.CompanyIndustry;
import com.fastech.systems.employeservice.model.Employee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface CompanyIndustryRepository extends JpaRepository<CompanyIndustry,Integer> {
    @Query("select c from CompanyIndustry c where c.company.companyID =?1 and c.industry.industryID=?2")
    CompanyIndustry existsByCompanyAndIndustry(Integer companyID, Integer industryID);

    @Query("select c from CompanyIndustry c where c.industry.industryID=?1")
    Page<CompanyIndustry> findAllByIndustryID(Integer industryID, Pageable pageable);

    @Query("select c from CompanyIndustry c where c.company.companyID=?1")
    Page<CompanyIndustry> findAllByCompanyID(Integer companyID, Pageable pageable);
}
