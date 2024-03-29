package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.Employee;
import com.fastech.systems.employeservice.model.Position;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface PositionRepository extends JpaRepository<Position,Integer> {
}
