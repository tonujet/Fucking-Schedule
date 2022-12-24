package com.example.rozklad.services;

import com.example.rozklad.DTOs.*;
import com.example.rozklad.dao.*;
import com.example.rozklad.entities.*;
import com.example.rozklad.exceptions.handlers.ItemHandler;
import com.example.rozklad.exceptions.schedules.MultipleScheduleException;
import com.example.rozklad.model.WeekDays;
import com.example.rozklad.services.Interfaces.ICRUDService;
import com.example.rozklad.services.Interfaces.IGetAllService;
import com.example.rozklad.services.Interfaces.ISearchService;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.*;

import static java.util.Comparator.*;

@Service
public class ScheduleService implements
        ICRUDService<ScheduleDTO>,
        IGetAllService<ScheduleDTO>,
        ISearchService<ScheduleDTO> {

    private final TimeService timeService;
    private final ScheduleRepo scheduleRepo;
    private final ItemHandler itemHandler;
    private final DbaService dbaService;
    private final GroupService groupService;
    private final DisciplineService disciplineService;
    private final TeacherService teacherService;
    private final String itemName;

    public ScheduleService(ScheduleRepo scheduleRepo,
                           ItemHandler itemHandler,
                           DbaService dbaService,
                           GroupService groupService,
                           DisciplineService disciplineService,
                           TeacherService teacherService,
                           TimeService timeService,
                           @Value("Schedule") String itemName) {
        this.scheduleRepo = scheduleRepo;
        this.itemHandler = itemHandler;
        this.dbaService = dbaService;
        this.groupService = groupService;
        this.disciplineService = disciplineService;
        this.teacherService = teacherService;
        this.itemName = itemName;
        this.timeService = timeService;
    }

    @Override
    public void create(ScheduleDTO scheduleDTO) {
        itemHandler.handleIsIdNull(scheduleDTO.getId(), itemName);
        saveToDB(scheduleDTO);
    }

    @Override
    public void update(ScheduleDTO scheduleDTO) {
        dbaService.get(scheduleRepo, itemName, scheduleDTO.getId());
        saveToDB(scheduleDTO);
    }

    @Override
    public ScheduleDTO get(long id) {
        Schedule schedule = dbaService.get(scheduleRepo, itemName, id);
        return ScheduleDTO.toDTO(
                schedule,
                TeacherDTO.toDTO(schedule.getTeacher()),
                DisciplineDTO.toDTO(schedule.getDiscipline()),
                GroupDTO.toDTO(schedule.getGroup(), null)
        );
    }

    @Override
    public void delete(long id) {
        dbaService.get(scheduleRepo, itemName, id);
        scheduleRepo.deleteById(id);
    }

    @Override
    public List<ScheduleDTO> getAll(int limit) {
        if (limit == 0) {
            return scheduleRepo
                    .findAll()
                    .stream()
                    .map(this::toDTO)
                    .toList();
        }
        return scheduleRepo
                .findAll(Pageable.ofSize(limit))
                .stream()
                .map(this::toDTO).toList();
    }

    @Override
    public List<ScheduleDTO> search(String query, int limit){
        if(limit == 0){
            return listToDTO(scheduleRepo.findAll(query));
        }
        return listToDTO(scheduleRepo.findAll(query, Pageable.ofSize(limit)).toList());
    }

    public HashMap<String, List<ScheduleDTO>> getFullSchedule(long id) {
        List<String> days = Arrays.stream(WeekDays.values()).filter(x -> x != WeekDays.SUNDAY)
                .map(Enum::toString).toList();
        System.out.println("days = " + days);
        List<Schedule> schedules = scheduleRepo.findAllByGroupId(id)
                .stream()
                .filter(schedule -> schedule.getTime() != null)
                .sorted(comparing(Schedule::getTime))
                .toList();
        List<List<ScheduleDTO>> scheduleDays = days.stream()
                .map(day -> setScheduleDay(day, schedules))
                .toList();

        HashMap<String, List<ScheduleDTO>> fullSchedule = new LinkedHashMap<>();
        for (int i = 0; i < days.size(); i++) {
            fullSchedule.put(days.get(i).toLowerCase(), scheduleDays.get(i));
        }
        return fullSchedule;

    }

    public List<ScheduleDTO> setScheduleDay(String weekDay, List<Schedule> schedules) {
        List<ScheduleDTO> result = new ArrayList<>();
        List<String> time = timeService.getStartTime();
        for (String t : time) {
            boolean isExistOnlyOneAnswer = false;
            for (Schedule schedule : schedules) {
                if (Objects.equals(schedule.getTime(), t) &&
                        Objects.equals(schedule.getDayOfWeek().toString(), weekDay)) {
                    if(isExistOnlyOneAnswer){
                        throw new MultipleScheduleException("There are one more schedule for identical time");
                    }
                    result.add(ScheduleDTO.toDTO(schedule,
                            TeacherDTO.toSimpleDTO(schedule.getTeacher()),
                            null,
                            null));
                    isExistOnlyOneAnswer = true;

                }
            }
            if(!isExistOnlyOneAnswer){
                result.add(ScheduleDTO.builder().id(0).build());
            }

        }
        return result;
    }

    public void saveToDB(ScheduleDTO scheduleDTO) {
        scheduleRepo.save(Schedule.toEntity(
                scheduleDTO,
                groupService.getGroup(scheduleDTO.getGroupDTO()),
                disciplineService.getDiscipline(scheduleDTO.getDisciplineDTO()),
                teacherService.getTeacher(scheduleDTO.getTeacherDTO())
        ));
    }

    public ScheduleDTO toDTO(Schedule schedule) {
        return ScheduleDTO.toDTO(
                schedule,
                TeacherDTO.toDTO(schedule.getTeacher()),
                DisciplineDTO.toDTO(schedule.getDiscipline()),
                GroupDTO.toDTO(schedule.getGroup(), null)
        );
    }
    public List<ScheduleDTO> listToDTO(List<Schedule> list){
        return list.stream()
                .map(this::toDTO)
                .toList();
    }


}
