package com.example.rozklad.dao;

import com.example.rozklad.entities.Teacher;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface TeacherRepo extends JpaRepository<Teacher, Long> {
    @Query("SELECT t FROM Teacher t " +
            "WHERE t.name LIKE %?1% OR t.email LIKE %?1% OR t.phone LIKE %?1% OR t.surname LIKE %?1%")
    Page<Teacher> findAll(String query, Pageable pageable);

    @Query("SELECT t FROM Teacher t " +
            "WHERE t.name LIKE %?1% OR t.email LIKE %?1% OR t.phone LIKE %?1% OR t.surname LIKE %?1%")
    List<Teacher> findAll(String query);
}
