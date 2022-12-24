package com.example.rozklad.services.Interfaces;

import org.springframework.http.ResponseEntity;

public interface ICRUDService<T> {
    void create(T t);
    void update(T t);
    T get(long id);
    void delete(long id);
}
