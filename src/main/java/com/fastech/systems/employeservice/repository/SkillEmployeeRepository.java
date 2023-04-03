package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.SkillEmployee;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface SkillEmployeeRepository extends JpaRepository<SkillEmployee,Integer> {
    @Query("select s from SkillEmployee s where s.employee.employeeID=?1")
    Page<SkillEmployee> findSkillsByEmployeeID(Integer employeeID, Pageable pageable);

    @Query("select s from SkillEmployee s where s.skill.skillID=?1")
    Page<SkillEmployee> findEmployeesBySkillID(Integer skillID, Pageable pageable);
}
