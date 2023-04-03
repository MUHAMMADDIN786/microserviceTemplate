package com.fastech.systems.employeservice.repository;

import com.fastech.systems.employeservice.model.Task;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

@Repository
public interface TaskRepository extends JpaRepository<Task,Integer> {
    @Query("select t from Task t where t.projectEmployee.project.projectID=?1")
    Page<Task> findTasksByProjectID(Integer id,Pageable pageable);
}
