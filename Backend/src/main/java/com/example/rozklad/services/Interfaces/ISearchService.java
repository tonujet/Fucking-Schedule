package com.example.rozklad.services.Interfaces;

import java.util.List;

public interface ISearchService<T> {
    List<T> search(String query, int limit);
}
