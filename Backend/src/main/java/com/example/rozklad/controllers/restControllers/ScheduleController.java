package com.example.rozklad.controllers.restControllers;

import com.example.rozklad.DTOs.ScheduleDTO;
import com.example.rozklad.controllers.restControllers.interfaces.ICRUDController;
import com.example.rozklad.controllers.restControllers.interfaces.IGetAllController;
import com.example.rozklad.controllers.restControllers.interfaces.ISearchController;
import com.example.rozklad.services.ScheduleService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping("/schedules")
public class ScheduleController implements
        ICRUDController<ScheduleDTO>,
        IGetAllController<ScheduleDTO>,
        ISearchController<ScheduleDTO> {

    private final ScheduleService scheduleService;

    public ScheduleController(ScheduleService scheduleService) {
        this.scheduleService = scheduleService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.create(scheduleDTO);
        return ResponseEntity.ok("Schedule was created");
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody ScheduleDTO scheduleDTO) {
        scheduleService.update(scheduleDTO);
        return ResponseEntity.ok("Schedule was updated");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<ScheduleDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(scheduleService.get(id));
    }


    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        scheduleService.delete(id);
        return ResponseEntity.ok("Schedule was deleted");
    }

    @Override
    @GetMapping
    public ResponseEntity<List<ScheduleDTO>> getAll(@RequestParam int limit) {
        return ResponseEntity.ok(scheduleService.getAll(limit));
    }

    @GetMapping("/full/{id}")
    public ResponseEntity<HashMap<String, List<ScheduleDTO>>> getFullSchedule(@PathVariable long id) {
        return ResponseEntity.ok(scheduleService.getFullSchedule(id));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<ScheduleDTO>> search(@RequestParam String query, @RequestParam int limit) {
        return ResponseEntity.ok(scheduleService.search(query, limit));
    }
}

