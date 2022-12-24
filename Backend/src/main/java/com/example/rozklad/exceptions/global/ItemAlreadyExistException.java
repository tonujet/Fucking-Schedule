package com.example.rozklad.exceptions.global;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class ItemAlreadyExistException extends RuntimeException{
    private String itemName;

    public ItemAlreadyExistException(String message, String itemName) {
        super(message);
        this.itemName = itemName;
    }
}
