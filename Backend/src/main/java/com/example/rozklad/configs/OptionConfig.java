package com.example.rozklad.configs;

import com.example.rozklad.model.Options.Option;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import java.util.List;

@Configuration
public class OptionConfig {
    @Bean("V1scheduleOptions")
    public List<Option> scheduleOptions() {
        return List.of(
                new Option("name", "String", false),
                new Option("teacher", "ENUM", "teachers", true),
                new Option("discipline", "ENUM", "disciplines", true),
                new Option("dayOfWeek", "weekDays", true),
                new Option("time", "ENUM","startsTime", true),
                new Option("classroom", "String", true),
                new Option("group", "ENUM", "groups", true)
        );
    }

    @Bean("V1disciplineOptions")
    public List<Option> disciplineOptions() {
        return List.of(
                new Option("name", "String", false)
        );
    }

    @Bean("V1groupOptions")
    public List<Option> groupOptions() {
        return List.of(
                new Option("name", "String", false),
                new Option("department", "ENUM", "departments", true),
                new Option("course", "String", true)
        );
    }

    @Bean("V1studentOptions")
    public List<Option> studentOptions() {
        return List.of(
                new Option("name", "String", false),
                new Option("group", "ENUM", "groups", true),
                new Option("email", "String", false),
                new Option("phone", "String", false)
        );
    }

    @Bean("V1teacherOptions")
    public List<Option> teacherOptions() {
        return List.of(
                new Option("name", "String", false),
                new Option("surname", "String", false),
                new Option("email", "String", false),
                new Option("phone", "String", false)
        );
    }

    @Bean("V1departmentOptions")
    public List<Option> departmentOptions() {
        return List.of(
                new Option("name", "String", false),
                new Option("shortName", "String", false),
                new Option("faculty", "ENUM", "faculties", true)
        );
    }

    @Bean("V1newsOptions")
    public List<Option> newsOptions() {
        return List.of(
                new Option("title", "String", false),
                new Option("description", "String", false),
                new Option("text", "Text", false),
                new Option("dateCreated", "DateValue", true),
                new Option("headerImg", "File", false)
        );
    }

    @Bean("V1facultyOptions")
    public List<Option> facultyOptions() {
        return List.of(
                new Option("name", "String", false),
                new Option("shortName", "String", false)
        );
    }
}
