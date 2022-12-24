package com.example.rozklad.configs;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class TimeConfig {
    @Bean
    public List<String> fullTime(){
        return List.of(
                "8:30",
                "10:05",
                "10:25",
                "12:00",
                "12:20",
                "13:55",
                "14:15",
                "15:50",
                "16:10",
                "17:45",
                "18:05",
                "19:40"
        );
    }
    @Bean
    public List<String> startsTime(){
        return List.of(
                "08:30",
                "10:25",
                "12:20",
                "14:15",
                "16:10",
//                "17:45",
                "18:05"
        );
    }
    @Bean
    public List<String> breakTime(){
        return List.of(
                "10:05",
                "12:00",
                "13:55",
                "15:50",
                "17:45",
                "19:40"
        );
    }
}
