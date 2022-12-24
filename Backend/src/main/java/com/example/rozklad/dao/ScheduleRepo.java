package com.example.rozklad.dao;

import com.example.rozklad.entities.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;


public interface ScheduleRepo extends JpaRepository<Schedule, Long> {
    List<Schedule> findAllByGroupId(long id);

    @Query("SELECT sc FROM Schedule sc LEFT JOIN sc.teacher t LEFT JOIN sc.group g LEFT JOIN sc.discipline d "
            + "WHERE t.name LIKE %?1% OR t.surname LIKE %?1% OR g.name LIKE %?1% OR d.name LIKE %?1%"
            + "OR sc.name LIKE %?1% OR sc.name LIKE %?1%"
            + "OR sc.time LIKE %?1%")
    Page<Schedule> findAll(@Param("query") String query, Pageable pageable);

    @Query("SELECT sc FROM Schedule sc LEFT JOIN sc.teacher t LEFT JOIN sc.group g LEFT JOIN sc.discipline d "
            + "WHERE t.name LIKE %?1% OR t.surname LIKE %?1% OR g.name LIKE %?1% OR d.name LIKE %?1%"
            + "OR sc.name LIKE %?1%  OR sc.name LIKE %?1%"
            + "OR sc.time LIKE %?1%" )
    List<Schedule> findAll(String query);

}
