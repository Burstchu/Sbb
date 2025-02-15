package ru.tsystems.school.service;

import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.model.Schedule;

import java.util.List;

public interface ScheduleService {

    List<ScheduleDto> findAll();

    List<Schedule> findAllSchedules();

}
