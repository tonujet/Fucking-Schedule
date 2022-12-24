package com.example.rozklad.controllers.restControllers;


import com.example.rozklad.DTOs.StudentDTO;
import com.example.rozklad.controllers.restControllers.interfaces.ICRUDController;
import com.example.rozklad.controllers.restControllers.interfaces.IGetAllController;
import com.example.rozklad.controllers.restControllers.interfaces.ISearchController;
import com.example.rozklad.services.StudentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/students")
public class StudentController implements
        ICRUDController<StudentDTO>,
        IGetAllController<StudentDTO>,
        ISearchController<StudentDTO> {
    private final StudentService studentService;

    public StudentController(StudentService studentService) {
        this.studentService = studentService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.create(studentDTO);
        return ResponseEntity.ok("Student was created");
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody StudentDTO studentDTO) {
        studentService.update(studentDTO);
        return ResponseEntity.ok("Student was updated");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<StudentDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(studentService.get(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        return ResponseEntity.ok("Student was deleted");
    }

    @Override
    @GetMapping
    public ResponseEntity<List<StudentDTO>> getAll(@RequestParam int limit) {
        return ResponseEntity.ok(studentService.getAll(limit));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<StudentDTO>> search(String query, int limit) {
        return ResponseEntity.ok(studentService.search(query, limit));
    }
}
