package com.example.rozklad.controllers.restControllers;

import com.example.rozklad.DTOs.DisciplineDTO;
import com.example.rozklad.DTOs.ScheduleDTO;
import com.example.rozklad.controllers.restControllers.interfaces.ICRUDController;
import com.example.rozklad.controllers.restControllers.interfaces.IGetAllController;
import com.example.rozklad.controllers.restControllers.interfaces.ISearchController;
import com.example.rozklad.services.DisciplineService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/disciplines")
public class DisciplineController implements
        ICRUDController<DisciplineDTO>,
        IGetAllController<DisciplineDTO>,
        ISearchController<DisciplineDTO> {
    private final DisciplineService disciplineService;

    public DisciplineController(DisciplineService disciplineService) {
        this.disciplineService = disciplineService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody DisciplineDTO disciplineDTO) {
        disciplineService.create(disciplineDTO);
        return ResponseEntity.ok("Discipline was created");
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody DisciplineDTO disciplineDTO) {
        disciplineService.update(disciplineDTO);
        return ResponseEntity.ok("Discipline was updated");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DisciplineDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(disciplineService.get(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        disciplineService.delete(id);
        return ResponseEntity.ok("Discipline was deleted");
    }

    @Override
    @GetMapping
    public ResponseEntity<List<DisciplineDTO>> getAll(@RequestParam int limit) {
        return ResponseEntity.ok(disciplineService.getAll(limit));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<DisciplineDTO>> search(@RequestParam String query, @RequestParam int limit){
        return ResponseEntity.ok(disciplineService.search(query, limit));
    }
}
