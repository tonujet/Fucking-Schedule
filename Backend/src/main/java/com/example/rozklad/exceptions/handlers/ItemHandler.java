package com.example.rozklad.exceptions.handlers;


import com.example.rozklad.exceptions.global.ItemIDisRedundantException;
import org.springframework.stereotype.Component;

@Component
public class ItemHandler {
    public void handleIsIdNull(long id, String itemName) {
        if (id != 0) {
            throw new ItemIDisRedundantException(
                    "Input id is forbidden. Please delete field id=" + id, id, itemName);
        }
    }
}
