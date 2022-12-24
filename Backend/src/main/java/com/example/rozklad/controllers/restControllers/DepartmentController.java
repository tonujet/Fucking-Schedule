package com.example.rozklad.controllers.restControllers;

import com.example.rozklad.DTOs.DepartmentDTO;
import com.example.rozklad.DTOs.GroupDTO;
import com.example.rozklad.controllers.restControllers.interfaces.ICRUDController;
import com.example.rozklad.controllers.restControllers.interfaces.IGetAllController;
import com.example.rozklad.controllers.restControllers.interfaces.ISearchController;
import com.example.rozklad.services.DepartmentService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/departments")
public class DepartmentController implements
        ICRUDController<DepartmentDTO>,
        IGetAllController<DepartmentDTO>,
        ISearchController<DepartmentDTO> {
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody DepartmentDTO departmentDTO) {
        departmentService.create(departmentDTO);
        return ResponseEntity.ok("Department was created");
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody DepartmentDTO departmentDTO) {
        departmentService.update(departmentDTO);
        return ResponseEntity.ok("Department was updated");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<DepartmentDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(departmentService.get(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        departmentService.delete(id);
        return ResponseEntity.ok("Department was deleted");
    }

    @Override
    @GetMapping
    public ResponseEntity<List<DepartmentDTO>> getAll(@RequestParam int limit) {
        return ResponseEntity.ok(departmentService.getAll(limit));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<DepartmentDTO>> search(String query, int limit) {
        return ResponseEntity.ok(departmentService.search(query, limit));
    }
}
