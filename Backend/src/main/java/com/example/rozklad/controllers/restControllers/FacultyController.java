package com.example.rozklad.controllers.restControllers;

import com.example.rozklad.DTOs.FacultyDTO;
import com.example.rozklad.DTOs.GroupDTO;
import com.example.rozklad.controllers.restControllers.interfaces.ICRUDController;
import com.example.rozklad.controllers.restControllers.interfaces.IGetAllController;
import com.example.rozklad.controllers.restControllers.interfaces.ISearchController;
import com.example.rozklad.services.FacultyService;
import com.example.rozklad.services.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/faculties")
public class FacultyController implements
        ICRUDController<FacultyDTO>,
        IGetAllController<FacultyDTO>,
        ISearchController<FacultyDTO> {
    private final FacultyService facultyService;

    public FacultyController(FacultyService facultyService) {
        this.facultyService = facultyService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody FacultyDTO facultyDTO) {
        facultyService.create(facultyDTO);
        return ResponseEntity.ok("Faculty was created");
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody FacultyDTO facultyDTO) {
        facultyService.update(facultyDTO);
        return ResponseEntity.ok("Faculty was updated");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<FacultyDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(facultyService.get(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        facultyService.delete(id);
        return ResponseEntity.ok("Faculty was deleted");
    }

    @Override
    @GetMapping
    public ResponseEntity<List<FacultyDTO>> getAll(@RequestParam int limit) {
        return ResponseEntity.ok(facultyService.getAll(limit));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<FacultyDTO>> search(String query, int limit) {
        return ResponseEntity.ok(facultyService.search(query, limit));
    }
}
