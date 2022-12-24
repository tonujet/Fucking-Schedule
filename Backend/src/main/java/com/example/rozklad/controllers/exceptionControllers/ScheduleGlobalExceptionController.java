package com.example.rozklad.controllers.exceptionControllers;

import com.example.rozklad.exceptions.ExceptionDetails;
import com.example.rozklad.exceptions.global.ItemNotFoundException;
import com.example.rozklad.exceptions.schedules.MultipleScheduleException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ScheduleGlobalExceptionController {
    @ExceptionHandler(MultipleScheduleException.class)
    public ResponseEntity<ExceptionDetails<String>> handleGlobalException(MultipleScheduleException e) {
        String errMessage = e.getName() + "NotFoundException";
        return ResponseEntity.badRequest().body(new ExceptionDetails<>(
                LocalDateTime.now(), errMessage, e.getMessage()
        ));
    }

}
