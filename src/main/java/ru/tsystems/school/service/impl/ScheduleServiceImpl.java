package ru.tsystems.school.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.ScheduleDao;
import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.mapper.ScheduleMapper;
import ru.tsystems.school.model.Schedule;
import ru.tsystems.school.service.ScheduleService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class ScheduleServiceImpl implements ScheduleService {

    private final ScheduleDao scheduleDao;
    private final ScheduleMapper scheduleMapper;

    public ScheduleServiceImpl(ScheduleDao scheduleDao, ScheduleMapper scheduleMapper) {
        this.scheduleDao = scheduleDao;
        this.scheduleMapper = scheduleMapper;
    }

    @Override
    public List<ScheduleDto> findAll() {
        return scheduleDao.findAll().stream().map(scheduleMapper::toDto).collect(Collectors.toList());

    }

    @Override
    public List<Schedule> findAllSchedules() {

        return scheduleDao.findAll();
    }
}
