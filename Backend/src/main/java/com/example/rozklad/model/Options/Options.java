package com.example.rozklad.model.Options;

import com.fasterxml.jackson.annotation.JsonProperty;

import java.util.List;

public abstract class Options {
    @JsonProperty
    private final List<Option> schedules;
    @JsonProperty
    private final List<Option> disciplines;
    @JsonProperty
    private final List<Option> groups;
    @JsonProperty
    private final List<Option> students;
    @JsonProperty
    private final List<Option> teachers;
    @JsonProperty
    private final List<Option> departments;
    @JsonProperty
    private final List<Option> news;
    @JsonProperty
    private final List<Option> faculties;

    public Options(List<Option> schedules,
                   List<Option> disciplines,
                   List<Option> groups,
                   List<Option> students,
                   List<Option> teachers,
                   List<Option> departments,
                   List<Option> news,
                   List<Option> faculties) {
        this.schedules = schedules;
        this.disciplines = disciplines;
        this.groups = groups;
        this.students = students;
        this.teachers = teachers;
        this.departments = departments;
        this.news = news;
        this.faculties = faculties;
    }

    @Override
    public String toString() {
        return "Options{" +
                "scheduleOptions=" + schedules +
                ", disciplineOptions=" + disciplines +
                ", groupOptions=" + groups +
                ", studentOptions=" + students +
                ", teacherOptions=" + teachers +
                ", departmentOptions=" + departments +
                ", newsOptions=" + news +
                ", facultyOptions=" + faculties +
                '}';
    }
}
