package com.example.rozklad.services;

import com.example.rozklad.DTOs.*;
import com.example.rozklad.dao.DepartmentRepo;
import com.example.rozklad.entities.Department;
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
public class DepartmentService implements
        ICRUDService<DepartmentDTO>,
        IGetAllService<DepartmentDTO>,
        ISearchService<DepartmentDTO> {

    private final DepartmentRepo departmentRepo;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final FacultyService facultyService;
    private final String itemName;

    public DepartmentService(DepartmentRepo departmentRepo,
                             ItemHandler itemHandler,
                             DbaService dbaService,
                             FacultyService facultyService,
                             @Value("department") String itemName) {
        this.departmentRepo = departmentRepo;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.facultyService = facultyService;
        this.itemName = itemName;
    }

    @Override
    public void create(DepartmentDTO departmentDTO) {
        itemHandler.handleIsIdNull(departmentDTO.getId(), itemName);
        saveToDB(departmentDTO);
    }

    @Override
    public void update(DepartmentDTO departmentDTO) {
        dbaService.get(departmentRepo, itemName, departmentDTO.getId());
        saveToDB(departmentDTO);
    }

    @Override
    public DepartmentDTO get(long id) {
        Department department = dbaService.get(departmentRepo, itemName, id);
        return DepartmentDTO.toDTO(
                department,
                FacultyDTO.toDTO(department.getFaculty())
        );
    }

    @Override
    public void delete(long id) {
        dbaService.get(departmentRepo, itemName, id);
        departmentRepo.deleteById(id);
    }

    @Override
    public List<DepartmentDTO> getAll(int limit) {
        if(limit == 0){
            return departmentRepo.findAll().stream().map(this::toDto).toList();
        }
        return departmentRepo.findAll(Pageable.ofSize(limit)).stream().map(this::toDto).toList();
    }

    @Override
    public List<DepartmentDTO> search(String query, int limit){
        if(limit == 0){
            return listToDTO(departmentRepo.findAll(query));
        }
        return listToDTO(departmentRepo.findAll(query, Pageable.ofSize(limit)).toList());
    }

    public void saveToDB(DepartmentDTO departmentDTO) {
        departmentRepo.save(Department.toEntity(
                departmentDTO,
                facultyService.getFaculty(departmentDTO.getFacultyDTO())
        ));
    }

    public Department getDepartment(DepartmentDTO departmentDTO) {
        if (departmentDTO != null) {
            return dbaService.get(departmentRepo, itemName, departmentDTO.getId());
        }
        return null;
    }
    public DepartmentDTO toDto(Department department){
        return DepartmentDTO.toDTO(
                department,
                FacultyDTO.toDTO(department.getFaculty())
        );
    }
    public List<DepartmentDTO> listToDTO(List<Department> list){
        return list.stream()
                .map(this::toDto)
                .toList();
    }
}
