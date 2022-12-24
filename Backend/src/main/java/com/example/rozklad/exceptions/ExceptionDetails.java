package com.example.rozklad.exceptions;

import lombok.Data;
import java.time.LocalDateTime;

@Data
public class ExceptionDetails<T> {
    private final LocalDateTime date;
    private final String errorMessage;
    private final T details;
}
