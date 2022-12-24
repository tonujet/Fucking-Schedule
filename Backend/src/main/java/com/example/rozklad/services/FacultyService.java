package com.example.rozklad.services;

import com.example.rozklad.DTOs.*;
import com.example.rozklad.dao.FacultyRepo;
import com.example.rozklad.entities.Faculty;
import com.example.rozklad.entities.Schedule;
import com.example.rozklad.exceptions.handlers.ItemHandler;
import com.example.rozklad.services.Interfaces.ICRUDService;
import com.example.rozklad.services.Interfaces.IGetAllService;
import com.example.rozklad.services.Interfaces.ISearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FacultyService implements
        ICRUDService<FacultyDTO>,
        IGetAllService<FacultyDTO>,
        ISearchService<FacultyDTO> {
    private final FacultyRepo facultyRepo;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final String itemName;

    public FacultyService(FacultyRepo facultyRepo,
                          ItemHandler itemHandler,
                          DbaService dbaService,
                          @Value("faculty") String itemName) {
        this.facultyRepo = facultyRepo;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.itemName = itemName;
    }

    @Override
    public void create(FacultyDTO facultyDTO) {
        itemHandler.handleIsIdNull(facultyDTO.getId(),itemName);
        saveToDB(facultyDTO);
    }

    @Override
    public void update(FacultyDTO facultyDTO) {
        dbaService.get(facultyRepo, itemName, facultyDTO.getId());
        saveToDB(facultyDTO);
    }

    @Override
    public FacultyDTO get(long id) {
        Faculty faculty = dbaService.get(facultyRepo, itemName, id);
        return FacultyDTO.toDTO(faculty);
    }

    @Override
    public void delete(long id) {
        dbaService.get(facultyRepo, itemName, id);
        facultyRepo.deleteById(id);
    }

    @Override
    public List<FacultyDTO> getAll(int limit) {
        if(limit == 0){
            return facultyRepo.findAll().stream().map(FacultyDTO::toDTO).toList();
        }
        return facultyRepo.findAll(Pageable.ofSize(limit)).stream().map(FacultyDTO::toDTO).toList();
    }

    @Override
    public List<FacultyDTO> search(String query, int limit){
        if(limit == 0){
            return listToDTO(facultyRepo.findAll(query));
        }
        return listToDTO(facultyRepo.findAll(query, Pageable.ofSize(limit)).toList());
    }

    public void saveToDB(FacultyDTO facultyDTO){
        facultyRepo.save(Faculty.toEntity(facultyDTO));
    }

    public Faculty getFaculty(FacultyDTO facultyDTO) {
        if (facultyDTO != null) {
            return dbaService.get(facultyRepo, itemName, facultyDTO.getId());
        }
        return null;
    }

    public List<FacultyDTO> listToDTO(List<Faculty> list){
        return list.stream()
                .map(FacultyDTO::toDTO)
                .toList();
    }
}
