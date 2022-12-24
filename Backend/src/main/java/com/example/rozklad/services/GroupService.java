package com.example.rozklad.services;

import com.example.rozklad.DTOs.*;
import com.example.rozklad.dao.GroupRepo;
import com.example.rozklad.entities.Group;
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
public class GroupService implements
        ICRUDService<GroupDTO>,
        IGetAllService<GroupDTO>,
        ISearchService<GroupDTO> {
    private final GroupRepo groupRepo;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final DepartmentService departmentService;
    private final String itemName;

    public GroupService(GroupRepo groupRepo,
                        ItemHandler itemHandler,
                        DbaService dbaService,
                        DepartmentService departmentService,
                        @Value("group") String itemName) {
        this.groupRepo = groupRepo;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.departmentService= departmentService;
        this.itemName = itemName;
    }

    @Override
    public void create(GroupDTO groupDTO) {
        itemHandler.handleIsIdNull(groupDTO.getId(), itemName);
        saveToDB(groupDTO);
    }

    @Override
    public void update(GroupDTO groupDTO) {
        dbaService.get(groupRepo, itemName, groupDTO.getId());
        saveToDB(groupDTO);
    }

    @Override
    public GroupDTO get(long id) {
        Group group = dbaService.get(groupRepo, itemName, id);
        return GroupDTO.toDTO(
                group,
                DepartmentDTO.toDTO(group.getDepartment(), null)
        );

    }

    @Override
    public void delete(long id) {
        dbaService.get(groupRepo, itemName, id);
        groupRepo.deleteById(id);
    }

    @Override
    public List<GroupDTO> getAll(int limit) {
        if(limit == 0){
            return groupRepo.findAll().stream().map(this::toDTO).toList();
        }
        return groupRepo.findAll(Pageable.ofSize(limit)).stream().map(this::toDTO).toList();
    }

    @Override
    public List<GroupDTO> search(String query, int limit){
        if(limit == 0){
            return listToDTO(groupRepo.findAll(query));
        }
        return listToDTO(groupRepo.findAll(query, Pageable.ofSize(limit)).toList());
    }

    public void saveToDB(GroupDTO groupDTO){
        groupRepo.save(Group.toEntity(
                groupDTO,
                departmentService.getDepartment(groupDTO.getDepartmentDTO())
        ));
    }

    public Group getGroup(GroupDTO groupDTO) {
        if(groupDTO != null){
            return dbaService.get(groupRepo, itemName, groupDTO.getId());
        }
        return null;
    }
    public GroupDTO toDTO(Group group){
        return GroupDTO.toDTO(
                group,
                DepartmentDTO.toDTO(group.getDepartment(), null)
        );
    }
    public List<GroupDTO> listToDTO(List<Group> list){
        return list.stream()
                .map(this::toDTO)
                .toList();
    }


}
