package com.example.rozklad.controllers.restControllers;

import com.example.rozklad.DTOs.NewsDTO;
import com.example.rozklad.DTOs.ScheduleDTO;
import com.example.rozklad.controllers.restControllers.interfaces.ICRUDController;
import com.example.rozklad.controllers.restControllers.interfaces.IGetAllController;
import com.example.rozklad.controllers.restControllers.interfaces.ISearchController;
import com.example.rozklad.entities.News;
import com.example.rozklad.services.NewsService;
import jakarta.validation.Valid;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.util.List;

@RestController
@RequestMapping("/news")
public class NewsController implements
        IGetAllController<NewsDTO>,
        ISearchController<NewsDTO> {

    private  final NewsService newsService;

    public NewsController(NewsService newsService) {
        this.newsService = newsService;
    }


    @PostMapping("/create")
    public ResponseEntity<Long> create(@Valid  @RequestBody NewsDTO newsDTO) {
        return ResponseEntity.ok(newsService.create(newsDTO));
    }


    @PutMapping("/update")
    public ResponseEntity<String> update(@Valid @RequestBody NewsDTO newsDTO) {
        newsService.update(newsDTO);
        return ResponseEntity.ok("News was updated");
    }

    @GetMapping("/{id}")
    public ResponseEntity<NewsDTO> get(@PathVariable long id) {
        return ResponseEntity.ok(newsService.get(id));
    }


    @DeleteMapping("/delete/{id}")
    public ResponseEntity<String> delete(@PathVariable long id) {
        newsService.delete(id);
        return ResponseEntity.ok("User was deleted");
    }

    @Override
    @GetMapping
    public ResponseEntity<List<NewsDTO>> getAll(@RequestParam int limit) {
        return ResponseEntity.ok(newsService.getAll(limit));
    }

    @Override
    @GetMapping("/search")
    public ResponseEntity<List<NewsDTO>> search(String query, int limit) {
        return ResponseEntity.ok(newsService.search(query, limit));
    }
}
