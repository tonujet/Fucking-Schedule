package com.example.rozklad.controllers.exceptionControllers;

import com.example.rozklad.exceptions.ExceptionDetails;
import com.example.rozklad.exceptions.global.ItemAlreadyExistException;
import com.example.rozklad.exceptions.global.ItemIDisRedundantException;
import com.example.rozklad.exceptions.global.ItemNotFoundException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionController {

    @ExceptionHandler(ItemNotFoundException.class)
    public ResponseEntity<ExceptionDetails<String>> handleGlobalException(ItemNotFoundException e) {
        String errMessage = e.getItemName() + "NotFoundException";
        return ResponseEntity.badRequest().body(new ExceptionDetails<>(
                LocalDateTime.now(), errMessage, e.getMessage()
        ));
    }
    @ExceptionHandler(ItemAlreadyExistException.class)
    public ResponseEntity<ExceptionDetails<String>> handleGlobalException(ItemAlreadyExistException e) {
        String errMessage = e.getItemName() + "AlreadyExistException";
        return ResponseEntity.badRequest().body(new ExceptionDetails<>(
                LocalDateTime.now(), errMessage, e.getMessage()
        ));
    }

    @ExceptionHandler(ItemIDisRedundantException.class)
    public ResponseEntity<ExceptionDetails<String>> handleGlobalException(ItemIDisRedundantException e) {
        String errMessage = e.getItemName() + " has redundant field: id = "+e.getId() ;
        return ResponseEntity.badRequest().body(new ExceptionDetails<>(
                LocalDateTime.now(), errMessage, e.getMessage()
        ));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<ExceptionDetails<Map<String, String>>> handleNotCorrectFieldException
            (MethodArgumentNotValidException e) {
        Map<String, String> hashMap = new HashMap<>();
        e.getBindingResult().getFieldErrors().forEach(error ->
                hashMap.put(error.getField(), error.getDefaultMessage()));
        return ResponseEntity.badRequest().body(
                new ExceptionDetails<>(
                        LocalDateTime.now(), "Uncorrect Fields", hashMap
                )
        );
    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<ExceptionDetails<String>> handleSQLException
            (SQLIntegrityConstraintViolationException e){
        return ResponseEntity.badRequest().body(new ExceptionDetails<>(
                LocalDateTime.now(), "Problem with DB request", e.getMessage()
        ));
    }


}
