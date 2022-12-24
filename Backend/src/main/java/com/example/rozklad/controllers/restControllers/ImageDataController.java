package com.example.rozklad.controllers.restControllers;

import com.example.rozklad.services.ImageDataService;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

@RestController
@RequestMapping("/img")
public class ImageDataController {
    private final ImageDataService imageDataService;

    public ImageDataController(ImageDataService imageDataService) {
        this.imageDataService = imageDataService;
    }

    @GetMapping("/{id}")
    public ResponseEntity<?> getImage(@PathVariable long id) {
        return ResponseEntity.status(HttpStatus.OK)
                .contentType(MediaType.IMAGE_PNG)
                .body(imageDataService.getImage(id));
    }

    @PostMapping("/create")
    public ResponseEntity<String> uploadImg(@RequestParam("file") MultipartFile multipartFile,
                                            @RequestParam long newsId) {
        imageDataService.uploadImage(multipartFile, newsId);
        return ResponseEntity.ok("Image was uploaded");
    }

    @PostMapping("/update")
    public ResponseEntity<String> updateImg(@RequestParam("file") MultipartFile multipartFile,
                                            @RequestParam long newsId) {
        imageDataService.updateImage(multipartFile, newsId);
        return ResponseEntity.ok("Image was updated");
    }
}
