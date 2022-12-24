package com.example.rozklad.controllers.restControllers.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface IGetAllController<T> {
    ResponseEntity<List<T>> getAll(int limit);
}
