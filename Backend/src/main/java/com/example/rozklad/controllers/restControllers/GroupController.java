package com.example.rozklad.controllers.restControllers;

import com.example.rozklad.DTOs.GroupDTO;
import com.example.rozklad.controllers.restControllers.interfaces.ICRUDController;
import com.example.rozklad.controllers.restControllers.interfaces.IGetAllController;
import com.example.rozklad.controllers.restControllers.interfaces.ISearchController;
import com.example.rozklad.services.GroupService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/groups")
public class GroupController implements
        ICRUDController<GroupDTO>,
        IGetAllController<GroupDTO>,
        ISearchController<GroupDTO> {

    private final GroupService groupService;

    public GroupController(GroupService groupService) {
        this.groupService = groupService;
    }

    @Override
    @PostMapping("/create")
    public ResponseEntity<String> create(@Valid @RequestBody GroupDTO groupDTO) {
        groupService.create(groupDTO);
        return ResponseEntity.ok("Group was created");
    }

    @Override
    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody GroupDTO groupDTO) {
        groupService.update(groupDTO);
        return ResponseEntity.ok("Group was updated");
    }

    @Override
    @GetMapping("/{id}")
    public ResponseEntity<GroupDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(groupService.get(id));
    }

    @Override
    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        groupService.delete(id);
        return ResponseEntity.ok("Group was deleted");
    }

    @Override
    @GetMapping
    public ResponseEntity<List<GroupDTO>> getAll(@RequestParam int limit) {
        return ResponseEntity.ok(groupService.getAll(limit));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<GroupDTO>> search(String query, int limit) {
        return ResponseEntity.ok(groupService.search(query, limit));
    }
}
