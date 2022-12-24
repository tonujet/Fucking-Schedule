package com.example.rozklad.controllers.restControllers.interfaces;

import org.springframework.http.ResponseEntity;

public interface ICRUDController<T> {
    ResponseEntity<String> create(T t);
    ResponseEntity<String> update(T t);
    ResponseEntity<T> get(long id);
    ResponseEntity<String> delete(long id);
}
