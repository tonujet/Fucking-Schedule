package com.example.rozklad.DTOs;

import com.example.rozklad.entities.Faculty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

@Getter
@Setter
@Builder
@NoArgsConstructor
@AllArgsConstructor
public class FacultyDTO {
    private long id;

    @NotBlank(message = "Faculty`s name is null")
    @Size(min = 2, max = 100, message = "Faculty`s name should be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Faculty`s name is null")
    @Size(min = 2, max = 10, message = "Faculty`s name should be between 2 and 10 characters")
    private String shortName;

    public static FacultyDTO toDTO(Faculty faculty){
        if(faculty!=null){
            return FacultyDTO.builder()
                    .id(faculty.getId())
                    .name(faculty.getName())
                    .shortName(faculty.getShortName())
                    .build();
        }
        return null;

    }
}
