package com.example.rozklad.dao;

import com.example.rozklad.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface StudentRepo extends JpaRepository<Student, Long> {

    @Query(value = "SELECT s FROM Student s LEFT JOIN s.group g " +
            "WHERE s.name LIKE %?1% OR s.phone LIKE %?1% OR s.email LIKE %?1%" +
            "OR g.name LIKE %?1%")
    Page<Student> findAll(String query, Pageable pageable);

    @Query(value = "SELECT s FROM Student s LEFT JOIN s.group g " +
            "WHERE s.name LIKE %?1% OR s.phone LIKE %?1% OR s.email LIKE %?1%" +
            "OR g.name LIKE %?1%")
    List<Student> findAll(String query);

}
