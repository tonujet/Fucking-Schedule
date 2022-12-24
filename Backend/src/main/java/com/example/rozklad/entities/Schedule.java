package com.example.rozklad.entities;

import com.example.rozklad.DTOs.ScheduleDTO;
import com.example.rozklad.model.WeekDays;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;

@Entity
@Table(name = "schedules")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Schedule {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;


    @Column(nullable = false, unique = true)
    private String name;

    @ManyToOne
    @JoinColumn(name = "teacher_id")
    @JsonManagedReference
    private Teacher teacher;

    @ManyToOne
    @JoinColumn(name = "discipline_id")
    @JsonManagedReference
    private Discipline discipline;

    @ManyToOne
    @JoinColumn(name = "group_id")
    @JsonManagedReference
    private Group group;

    @Enumerated(EnumType.STRING)
    private WeekDays dayOfWeek;

    private String time;

    private String classroom;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    public static Schedule toEntity(ScheduleDTO scheduleDTO, Group group, Discipline discipline, Teacher teacher){
        return Schedule.builder()
                .id(scheduleDTO.getId())
                .name(scheduleDTO.getName())
                .dayOfWeek(scheduleDTO.getDayOfWeek())
                .time(scheduleDTO.getTime())
                .classroom(scheduleDTO.getClassroom())
                .teacher(teacher)
                .group(group)
                .discipline(discipline)
                .build();
    }

}
