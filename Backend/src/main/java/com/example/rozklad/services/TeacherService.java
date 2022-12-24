package com.example.rozklad.services;

import com.example.rozklad.DTOs.*;
import com.example.rozklad.dao.TeacherRepo;
import com.example.rozklad.entities.Schedule;
import com.example.rozklad.entities.Student;
import com.example.rozklad.entities.Teacher;
import com.example.rozklad.exceptions.handlers.ItemHandler;
import com.example.rozklad.services.Interfaces.ICRUDService;
import com.example.rozklad.services.Interfaces.IGetAllService;
import com.example.rozklad.services.Interfaces.ISearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class TeacherService implements
        ICRUDService<TeacherDTO>,
        IGetAllService<TeacherDTO>,
        ISearchService<TeacherDTO> {
    private final TeacherRepo teacherRepo;
    private final DbaService dbaService;
    private final ItemHandler itemHandler;
    private final String itemName;

    public TeacherService(TeacherRepo teacherRepo,
                          DbaService dbaService,
                          ItemHandler itemHandler,
                          @Value("teacher") String itemName) {
        this.teacherRepo = teacherRepo;
        this.dbaService = dbaService;
        this.itemHandler = itemHandler;
        this.itemName = itemName;
    }

    @Override
    public void create(TeacherDTO teacherDTO) {
        itemHandler.handleIsIdNull(teacherDTO.getId(), itemName);
        saveToDB(teacherDTO);
    }

    @Override
    public void update(TeacherDTO teacherDTO) {
        dbaService.get(teacherRepo, itemName, teacherDTO.getId());
        saveToDB(teacherDTO);
    }

    @Override
    public TeacherDTO get(long id) {
        return TeacherDTO.toDTO(
                dbaService.get(teacherRepo, itemName, id)
        );
    }

    @Override
    public void delete(long id) {
        dbaService.get(teacherRepo, itemName, id);
        teacherRepo.deleteById(id);
    }

    @Override
    public List<TeacherDTO> getAll(int limit) {
        if(limit == 0){
            return teacherRepo.findAll().stream().map(TeacherDTO::toDTO).toList();
        }
        return teacherRepo.findAll(Pageable.ofSize(limit)).stream().map(TeacherDTO::toDTO).toList();
    }

    @Override
    public List<TeacherDTO> search(String query, int limit){
        if(limit == 0){
            return listToDTO(teacherRepo.findAll(query));
        }
        return listToDTO(teacherRepo.findAll(query, Pageable.ofSize(limit)).toList());
    }

    public Teacher getTeacher(TeacherDTO teacherDTO) {
        if (teacherDTO != null) {
            return dbaService.get(teacherRepo, itemName, teacherDTO.getId());
        }
        return null;
    }

    public void saveToDB(TeacherDTO teacherDTO) {
        teacherRepo.save(Teacher.toEntity(
                teacherDTO
        ));
    }

    public List<TeacherDTO> listToDTO(List<Teacher> list){
        return list.stream()
                .map(TeacherDTO::toDTO)
                .toList();
    }
}
