package ru.tsystems.school.service;

import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;
import ru.tsystems.school.model.Schedule;

import java.time.LocalTime;
import java.util.List;

public interface StationService {

    List<StationDto> findAllDtoStations();

    StationDto findDtoById(int id);

    void save(StationDto stationDto);

    void deleteById(int id);

    List<TrainDto> findAllTrains(int id);

    List<Schedule> findAllSchedulesForStation(int id);

    List<ScheduleDto> findAllSchedulesDtoForStation(int id);

    void saveSchedule2(int stationId, int trainId, LocalTime arrivalTime);

    void saveSchedule(Schedule schedule);

}
