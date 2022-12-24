package com.example.rozklad.services.Interfaces;
import java.util.List;

public interface IGetAllService<T> {
    List<T> getAll(int limit);
}
