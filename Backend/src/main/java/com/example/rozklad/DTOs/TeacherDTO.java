package com.example.rozklad.DTOs;

import com.example.rozklad.entities.Teacher;
import jakarta.validation.constraints.Email;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.Builder;
import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
@Builder
public class TeacherDTO {
    private long id;

    @NotBlank(message = "Teacher`s name is null")
    @Size(min = 2, max = 100, message = "Teacher`s name should be between 2 and 100 characters")
    private String name;

    @NotBlank(message = "Teacher`s surname is null")
    @Size(min = 2, max = 100, message = "Teacher`s surname should be between 2 and 100 characters")
    private String surname;

    @NotBlank(message = "Teacher`s email is null")
    @Email(message = "Teacher`s email is incorrect")
    private String email;

    @NotBlank(message = "Teacher`s phone is null")
    @Size(min = 18, max = 18, message = "Teacher`s phone is not correct")
    private String phone;
    public static TeacherDTO toDTO(Teacher teacher){
        if(teacher != null){
            return TeacherDTO.builder()
                    .id(teacher.getId())
                    .name(teacher.getName())
                    .surname(teacher.getSurname())
                    .email(teacher.getEmail())
                    .phone(teacher.getPhone())
                    .build();
        }
        return null;
    }
    public static TeacherDTO toSimpleDTO(Teacher teacher){
        if(teacher != null){
            return TeacherDTO.builder()
                    .id(teacher.getId())
                    .name(teacher.getName())
                    .surname(teacher.getSurname())
                    .build();
        }
        return null;
    }
}
