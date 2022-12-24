package com.example.rozklad.dao;

import com.example.rozklad.entities.Faculty;
import com.example.rozklad.entities.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface FacultyRepo extends JpaRepository<Faculty, Long> {
    @Query("SELECT f FROM Faculty f "
            + "WHERE f.name LIKE %?1% OR f.shortName LIKE %?1%")
    Page<Faculty> findAll(@Param("query") String query, Pageable pageable);

    @Query("SELECT f FROM Faculty f "
            + "WHERE f.name LIKE %?1% OR f.shortName LIKE %?1%")
    List<Faculty> findAll(String query);
}
