package com.example.rozklad.services;


import com.example.rozklad.DTOs.*;
import com.example.rozklad.dao.StudentRepo;
import com.example.rozklad.entities.Schedule;
import com.example.rozklad.entities.Student;
import com.example.rozklad.exceptions.handlers.ItemHandler;
import com.example.rozklad.services.Interfaces.ICRUDService;
import com.example.rozklad.services.Interfaces.IGetAllService;
import com.example.rozklad.services.Interfaces.ISearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.IntStream;

@Service
public class StudentService implements
        ICRUDService<StudentDTO>,
        IGetAllService<StudentDTO>,
        ISearchService<StudentDTO> {

    private final StudentRepo studentRepo;
    private final GroupService groupService;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final String itemName;

    public StudentService(StudentRepo studentRepo,
                          GroupService groupService,
                          ItemHandler itemHandler,
                          DbaService dbaService,
                          @Value("student") String itemName) {
        this.studentRepo = studentRepo;
        this.groupService = groupService;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.itemName = itemName;
    }

    @Override
    public void create(StudentDTO studentDTO) {
        itemHandler.handleIsIdNull(studentDTO.getId(),itemName);
        saveToDB(studentDTO);
    }

    @Override
    public void update(StudentDTO studentDTO) {
        dbaService.get(studentRepo, itemName, studentDTO.getId());
        saveToDB(studentDTO);
    }

    @Override
    public StudentDTO get(long id) {
        Student student = dbaService.get(studentRepo, itemName, id);
        return StudentDTO.toDTO(
                student,
                GroupDTO.toDTO(student.getGroup(),null)
        );
    }

    @Override
    public void delete(long id) {
        dbaService.get(studentRepo, itemName, id);
        studentRepo.deleteById(id);
    }

    @Override
    public List<StudentDTO> getAll(int limit) {
        if(limit == 0){
            return studentRepo.findAll().stream().map(this::toDTO).toList();
        }
        return studentRepo.findAll(Pageable.ofSize(limit)).stream().map(this::toDTO).toList();
    }

    @Override
    public List<StudentDTO> search(String query, int limit){
        if(limit == 0){
            return listToDTO(studentRepo.findAll(query));
        }
        return listToDTO(studentRepo.findAll(query, Pageable.ofSize(limit)).toList());
    }
    public void saveToDB(StudentDTO studentDTO){
        studentRepo.save(Student.toEntity(
                studentDTO,
                groupService.getGroup(studentDTO.getGroupDTO())
        ));
    }
    public StudentDTO toDTO(Student student){
        return StudentDTO.toDTO(
                student,
                GroupDTO.toDTO(student.getGroup(), null)
        );
    }

    public List<StudentDTO> listToDTO(List<Student> list){
        return list.stream()
                .map(this::toDTO)
                .toList();
    }
}
