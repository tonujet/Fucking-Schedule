package com.example.rozklad.controllers.restControllers;

import com.example.rozklad.services.OptionService;
import com.example.rozklad.model.Options.Options;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/options")
public class OptionController {

    private final OptionService optionService;

    public OptionController(OptionService optionService) {
        this.optionService = optionService;
    }

    @GetMapping
    public ResponseEntity<Options> getOptions(){
        return ResponseEntity.ok(optionService.getOptions());
    }
}
