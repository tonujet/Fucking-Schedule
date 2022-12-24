package com.example.rozklad.entities;

import com.example.rozklad.DTOs.DisciplineDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "disciplines")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Discipline {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @OneToMany(mappedBy = "discipline")
    @JsonBackReference
    private List<Schedule> schedules;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    public static Discipline toEntity(DisciplineDTO dto){
        return Discipline.builder()
                .id(dto.getId())
                .name(dto.getName())
                .build();
    }

    @PreRemove
    private void preRemove(){
        for (Schedule s: schedules) {
            s.setDiscipline(null);
        }
    }
}
