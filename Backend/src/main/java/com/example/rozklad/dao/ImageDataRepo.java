package com.example.rozklad.dao;


import com.example.rozklad.entities.ImageData;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ImageDataRepo extends JpaRepository<ImageData, Long> {
}
