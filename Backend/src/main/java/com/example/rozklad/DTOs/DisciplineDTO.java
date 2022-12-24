package com.example.rozklad.DTOs;

import com.example.rozklad.entities.Discipline;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DisciplineDTO {
    private long id;

    @NotBlank(message = "Discipline`s name is null")
    @Size(min = 2, max = 100, message = "Discipline`s name should be between 2 and 100 characters")
    private String name;

    public static DisciplineDTO toDTO(Discipline d){
        if(d!=null){
            return DisciplineDTO.builder()
                    .id(d.getId())
                    .name(d.getName())
                    .build();
        }
        return null;

    }
}
