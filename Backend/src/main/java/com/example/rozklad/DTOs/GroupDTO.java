package com.example.rozklad.DTOs;

import com.example.rozklad.entities.Group;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.*;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class GroupDTO {
    private long id;

    @NotBlank(message = "Group`s name is null")
    @Size(min = 2, max = 100, message = "Group`s name should be between 2 and 100 characters")
    private String name;

    @JsonProperty("department")
    private DepartmentDTO departmentDTO;

    @Pattern(regexp = "^[1-6]$", message = "Group`s course should be between 1 and 6")
    @Size(min = 1, max = 1, message = "Group`s course should be between 1 and 6")
    private String course;

    public static GroupDTO toDTO(Group group, DepartmentDTO departmentDTO) {
        if(group !=null){
            return GroupDTO.builder()
                    .id(group.getId())
                    .name(group.getName())
                    .course(group.getCourse())
                    .departmentDTO(departmentDTO)
                    .build();
        }
        return null;

    }
}
