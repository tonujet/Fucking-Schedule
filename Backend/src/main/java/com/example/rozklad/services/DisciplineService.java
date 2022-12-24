package com.example.rozklad.services;


import com.example.rozklad.DTOs.*;
import com.example.rozklad.dao.DisciplineRepo;
import com.example.rozklad.entities.Discipline;
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
public class DisciplineService implements
        ICRUDService<DisciplineDTO>,
        IGetAllService<DisciplineDTO>,
        ISearchService<DisciplineDTO> {
    private final DbaService dbaService;
    private final DisciplineRepo disciplineRepo;
    private final ItemHandler itemHandler;
    private final String itemName;


    public DisciplineService(DbaService dbaService,
                             DisciplineRepo disciplineRepo,
                             ItemHandler itemHandler,
                             @Value("discipline") String itemName) {
        this.dbaService = dbaService;
        this.disciplineRepo = disciplineRepo;
        this.itemHandler = itemHandler;
        this.itemName = itemName;
    }

    @Override
    public void create(DisciplineDTO disciplineDTO) {
        itemHandler.handleIsIdNull(disciplineDTO.getId(), itemName);
        saveToDB(disciplineDTO);
    }

    @Override
    public void update(DisciplineDTO disciplineDTO) {
        dbaService.get(disciplineRepo, itemName, disciplineDTO.getId());
        saveToDB(disciplineDTO);
    }

    @Override
    public DisciplineDTO get(long id) {
        Discipline discipline = dbaService.get(disciplineRepo, itemName, id);
        return DisciplineDTO.toDTO(discipline);
    }

    @Override
    public void delete(long id) {
        dbaService.get(disciplineRepo, itemName, id);
        disciplineRepo.deleteById(id);
    }

    @Override
    public List<DisciplineDTO> getAll(int limit) {
        if(limit == 0){
            return disciplineRepo.findAll().stream().map(DisciplineDTO::toDTO).toList();
        }
        return disciplineRepo.findAll(Pageable.ofSize(limit)).stream().map(DisciplineDTO::toDTO).toList();
    }

    @Override
    public List<DisciplineDTO> search(String query, int limit){
        if(limit == 0){
            return listToDTO(disciplineRepo.findAll(query));
        }
        return listToDTO(disciplineRepo.findAll(query, Pageable.ofSize(limit)).toList());
    }

    public void saveToDB(DisciplineDTO disciplineDTO) {
        disciplineRepo.save(Discipline.toEntity(disciplineDTO));
    }

    public Discipline getDiscipline(DisciplineDTO disciplineDTO) {
        if (disciplineDTO != null) {
            return dbaService.get(disciplineRepo, itemName, disciplineDTO.getId());
        }
        return null;
    }
    public List<DisciplineDTO> listToDTO(List<Discipline> list){
        return list.stream()
                .map(DisciplineDTO::toDTO)
                .toList();
    }
}
