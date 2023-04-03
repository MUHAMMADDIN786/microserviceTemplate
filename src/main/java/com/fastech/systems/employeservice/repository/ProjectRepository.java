package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.Project;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface ProjectRepository extends JpaRepository<Project,Integer> {
    @Query("select p from Project p where p.companyBranch.companyBranchID=?1")
    Page<Project> findAllProjectsByCompanyBranchID(Integer companyBranchID, Pageable pageable);
}
