package com.example.rozklad.services;

import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TimeService {
    private final List<String> fullTime;
    private final List<String> startsTime;
    private final List<String> breakTime;

    public TimeService(List<String> fullTime,
                       List<String> startsTime,
                       List<String> breakTime) {
        this.fullTime = fullTime;
        this.startsTime = startsTime;
        this.breakTime = breakTime;
    }

    public List<String> getFullTime(){
        return fullTime;
    }
    public List<String> getStartTime(){
        return startsTime;
    }
    public List<String> getBreakTime(){
       return breakTime;
    }
}
