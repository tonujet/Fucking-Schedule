package com.example.rozklad.controllers.restControllers.interfaces;

import org.springframework.http.ResponseEntity;

import java.util.List;

public interface ISearchController<T> {
    ResponseEntity<List<T>> search(String query, int limit);
}
