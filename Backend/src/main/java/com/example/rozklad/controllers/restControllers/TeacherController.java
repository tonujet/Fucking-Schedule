package com.example.rozklad.controllers.restControllers;

import com.example.rozklad.DTOs.TeacherDTO;
import com.example.rozklad.controllers.restControllers.interfaces.ICRUDController;
import com.example.rozklad.controllers.restControllers.interfaces.IGetAllController;
import com.example.rozklad.controllers.restControllers.interfaces.ISearchController;
import com.example.rozklad.services.TeacherService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/teachers")
public class TeacherController implements
        ICRUDController<TeacherDTO>,
        IGetAllController<TeacherDTO>,
        ISearchController<TeacherDTO> {
    private final TeacherService teacherService;

    public TeacherController(TeacherService teacherService) {
        this.teacherService = teacherService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody TeacherDTO teacherDTO) {
        teacherService.create(teacherDTO);
        return ResponseEntity.ok("Teacher was created");
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody TeacherDTO teacherDTO) {
        teacherService.update(teacherDTO);
        return ResponseEntity.ok("Teacher was updated");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<TeacherDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(teacherService.get(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        teacherService.delete(id);
        return ResponseEntity.ok("Teacher was deleted");
    }

    @Override
    @GetMapping
    public ResponseEntity<List<TeacherDTO>> getAll(@RequestParam int limit) {
        return ResponseEntity.ok(teacherService.getAll(limit));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<TeacherDTO>> search(String query, int limit) {
        return ResponseEntity.ok(teacherService.search(query, limit));
    }
}
