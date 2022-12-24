package com.example.rozklad.DTOs;


import com.example.rozklad.entities.*;
import com.example.rozklad.model.WeekDays;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Builder
@Setter
@ToString
public class ScheduleDTO {

    private long id;

    @NotBlank(message = "Schedule name is null")
    @Size(min = 2, max = 100, message = "Schedules name should be between 2 and 100 characters")
    private String name;

    @JsonProperty("teacher")
    private TeacherDTO teacherDTO;

    @JsonProperty("discipline")
    private DisciplineDTO disciplineDTO;

    private WeekDays dayOfWeek;

    @Pattern(regexp = "^(0[0-9]|1[0-9]|2[0-3]):[0-5][0-9]$", message = "Schedules time is incorrect")
    private String time;

    @Size(min = 1, max = 50, message = "Schedules classroom should be between 1 and 50 characters")
    private String classroom;

    @JsonProperty("group")
    private GroupDTO groupDTO;

    public static ScheduleDTO toDTO(Schedule schedule, TeacherDTO teacherDTO, DisciplineDTO disciplineDTO, GroupDTO groupDTO){
        return ScheduleDTO.builder()
                .id(schedule.getId())
                .name(schedule.getName())
                .dayOfWeek(schedule.getDayOfWeek())
                .time(schedule.getTime())
                .classroom(schedule.getClassroom())
                .teacherDTO(teacherDTO)
                .groupDTO(groupDTO)
                .disciplineDTO(disciplineDTO)
                .build();
    }



}
