package com.example.rozklad.dao;

import com.example.rozklad.entities.Department;
import com.example.rozklad.entities.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DepartmentRepo extends JpaRepository<Department, Long> {
    @Query("SELECT d FROM Department d LEFT JOIN d.faculty f "
            + "WHERE d.name LIKE %?1% OR d.shortName LIKE %?1% "
            + "OR f.name LIKE %?1% OR f.shortName LIKE %?1%")
    Page<Department> findAll(@Param("query") String query, Pageable pageable);

    @Query("SELECT d FROM Department d LEFT JOIN d.faculty f "
            + "WHERE d.name LIKE %?1% OR d.shortName LIKE %?1% "
            + "OR f.name LIKE %?1% OR f.shortName LIKE %?1%")
    List<Department> findAll(String query);
}
