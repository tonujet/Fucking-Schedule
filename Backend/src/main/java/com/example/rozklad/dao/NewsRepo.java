package com.example.rozklad.dao;

import com.example.rozklad.entities.News;
import com.example.rozklad.entities.Schedule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface NewsRepo extends JpaRepository<News, Long> {
    @Query("SELECT n FROM News n "
            + "WHERE n.title LIKE %?1% OR n.description LIKE %?1% OR" +
            " n.text LIKE %?1% ")
    Page<News> findAll(@Param("query") String query, Pageable pageable);

    @Query("SELECT n FROM News n "
            + "WHERE n.title LIKE %?1% OR n.description LIKE %?1% OR" +
            " n.text LIKE %?1% ")
    List<News> findAll(String query);
}
