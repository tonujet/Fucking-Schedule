package com.example.rozklad.dao;

import com.example.rozklad.entities.Group;
import com.example.rozklad.entities.Student;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;

public interface GroupRepo extends JpaRepository<Group, Long> {
    @Query(value = "SELECT g FROM Group g LEFT JOIN g.department d " +
            "WHERE g.name LIKE %?1% OR g.course  LIKE %?1%" +
            "OR d.name LIKE %?1%")
    Page<Group> findAll(String query, Pageable pageable);

    @Query(value = "SELECT g FROM Group g LEFT JOIN g.department d " +
            "WHERE g.name LIKE %?1% OR g.course  LIKE %?1%" +
            "OR d.name LIKE %?1%")
    List<Group> findAll(String query);
}
