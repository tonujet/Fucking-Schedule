package com.example.rozklad.exceptions.global;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemIDisRedundantException extends RuntimeException{
    private final long id;
    private final String itemName;

    public ItemIDisRedundantException(String message, long id, String itemName) {
        super(message);
        this.id = id;
        this.itemName = itemName;
    }
}
