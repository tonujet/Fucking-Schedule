package com.example.rozklad.dao;

import com.example.rozklad.entities.Discipline;
import com.example.rozklad.entities.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface DisciplineRepo extends JpaRepository<Discipline, Long> {
    @Query("SELECT d FROM Discipline d  "
            + "WHERE d.name LIKE %?1%")
    Page<Discipline> findAll(@Param("query") String query, Pageable pageable);

    @Query("SELECT d FROM Discipline d  "
            + "WHERE d.name LIKE %?1%")
    List<Discipline> findAll(String query);
}
