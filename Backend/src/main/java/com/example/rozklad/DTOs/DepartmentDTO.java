package com.example.rozklad.DTOs;

import com.example.rozklad.entities.Department;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class DepartmentDTO {
    private long id;

    @NotBlank(message = "Department`s name is null")
    @Size(min = 2, max = 100, message = "Department`s name should be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Department`s shortName is null")
    @Size(min = 2, max = 10, message = "Department`s shortName should be between 2 and 10 characters")
    private String shortName;

    @JsonProperty("faculty")
    private FacultyDTO facultyDTO;


    public static DepartmentDTO toDTO(Department d, FacultyDTO facultyDTO){
       if(d != null){
           return DepartmentDTO.builder()
                   .id(d.getId())
                   .name(d.getName())
                   .shortName(d.getShortName())
                   .facultyDTO(facultyDTO)
                   .build();
       }
       return null;
    }

}
