package com.example.rozklad.DTOs;

import com.example.rozklad.entities.Student;
import com.fasterxml.jackson.annotation.JsonProperty;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class StudentDTO {
    private long id;

    @JsonProperty("group")
    private GroupDTO groupDTO;

    @NotBlank(message = "Student`s name is null")
    @Size(min = 2, max = 100, message = "Student name should be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Student`s email is null")
    @Email(message = "Student`s email is incorrect")
    private String email;

    @NotBlank(message = "Student`s phone is null")
    @Size(min = 18, max = 18, message = "Student`s phone is not correct")
    private String phone;

    public static StudentDTO toDTO(Student student, GroupDTO groupDTO){
        if(student !=null){
            return StudentDTO.builder()
                    .id(student.getId())
                    .name(student.getName())
                    .email(student.getEmail())
                    .phone(student.getPhone())
                    .groupDTO(groupDTO)
                    .build();
        }
        return null;

    }
}
