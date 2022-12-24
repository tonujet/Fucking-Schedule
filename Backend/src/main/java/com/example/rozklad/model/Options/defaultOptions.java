package com.example.rozklad.model.Options;

import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.stereotype.Component;

import java.util.List;

@Component("default")
public class defaultOptions extends Options {
    public defaultOptions(@Qualifier("V1scheduleOptions") List<Option> schedules,
                          @Qualifier("V1disciplineOptions") List<Option> disciplines,
                          @Qualifier("V1groupOptions") List<Option> groups,
                          @Qualifier("V1studentOptions") List<Option> students,
                          @Qualifier("V1teacherOptions") List<Option> teachers,
                          @Qualifier("V1departmentOptions") List<Option> departments,
                          @Qualifier("V1newsOptions") List<Option> news,
                          @Qualifier("V1facultyOptions") List<Option> faculties) {
        super(schedules, disciplines, groups, students, teachers, departments, news, faculties);
    }
}
