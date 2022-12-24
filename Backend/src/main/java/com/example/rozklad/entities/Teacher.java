package com.example.rozklad.entities;

import com.example.rozklad.DTOs.TeacherDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "teachers")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Teacher {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false)
    private String name;

    @Column(nullable = false)
    private String surname;

    @Column(nullable = false, unique = true)
    private String email;

    @Column(nullable = false, unique = true)
    private String phone;

    @OneToMany(mappedBy = "teacher")
    @JsonBackReference
    List<Schedule> schedules;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    public static Teacher toEntity(TeacherDTO teacherDTO){
        return Teacher.builder()
                .id(teacherDTO.getId())
                .name(teacherDTO.getName())
                .surname(teacherDTO.getSurname())
                .email(teacherDTO.getEmail())
                .phone(teacherDTO.getPhone())
                .build();
    }

    @PreRemove
    private void preRemove(){
        for (Schedule s: schedules) {
            s.setTeacher(null);
        }
    }
}
