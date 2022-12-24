package com.example.rozklad.controllers.restControllers;

import com.example.rozklad.services.TimeService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/time")
public class TimeController {
    private final TimeService timeService;

    public TimeController(TimeService timeService) {
        this.timeService = timeService;
    }

    @RequestMapping("/fulltime")
    public ResponseEntity<List<String>> getFullTime(){
        return ResponseEntity.ok(timeService.getFullTime());
    }

    @RequestMapping("/startsTime")
    public ResponseEntity<List<String>> getStartTime(){
        return ResponseEntity.ok(timeService.getStartTime());
    }

    @RequestMapping("/breakTime")
    public ResponseEntity<List<String>> getBreakTime(){
        return ResponseEntity.ok(timeService.getBreakTime());
    }
}
