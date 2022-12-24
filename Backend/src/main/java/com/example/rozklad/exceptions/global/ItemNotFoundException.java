package com.example.rozklad.exceptions.global;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemNotFoundException extends RuntimeException{
    private String itemName;
    public ItemNotFoundException(String message, String itemName) {
        super(message);
        this.itemName = itemName;
    }
}
