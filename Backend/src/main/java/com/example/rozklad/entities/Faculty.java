package com.example.rozklad.entities;

import com.example.rozklad.DTOs.FacultyDTO;
import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonManagedReference;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Pattern;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.hibernate.annotations.CreationTimestamp;
import org.hibernate.annotations.UpdateTimestamp;

import java.time.LocalDateTime;
import java.util.List;

@Entity
@Table(name = "faculties")
@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class Faculty {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private long id;

    @Column(nullable = false, unique = true)
    private String name;

    @Column(nullable = false)
    private String shortName;

    @OneToMany(mappedBy = "faculty")
    @JsonBackReference
    private List<Department> departments;

    @CreationTimestamp
    @Column(nullable = false, updatable = false)
    private LocalDateTime dateCreated;

    @UpdateTimestamp
    private LocalDateTime dateUpdated;

    public static Faculty toEntity(FacultyDTO dto){
        return Faculty.builder()
                .id(dto.getId())
                .name(dto.getName())
                .shortName(dto.getShortName())
                .build();
    }

    @PreRemove
    private void preRemove() {
        for (Department d : departments) {
            d.setFaculty(null);
        }
    }

}
