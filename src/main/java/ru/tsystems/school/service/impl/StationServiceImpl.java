package ru.tsystems.school.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.StationDao;
import ru.tsystems.school.dto.ScheduleDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;
import ru.tsystems.school.mapper.ScheduleMapper;
import ru.tsystems.school.mapper.StationMapper;
import ru.tsystems.school.mapper.TrainMapper;
import ru.tsystems.school.model.Schedule;
import ru.tsystems.school.model.Station;
import ru.tsystems.school.model.Train;
import ru.tsystems.school.service.StationService;

import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class StationServiceImpl implements StationService {

    private final StationDao stationDao;
    private final StationMapper stationMapper;
    private final TrainMapper trainMapper;
    private final ScheduleMapper scheduleMapper;

    public StationServiceImpl(StationDao stationDao, StationMapper stationMapper, TrainMapper trainMapper, ScheduleMapper scheduleMapper) {
        this.stationDao = stationDao;
        this.stationMapper = stationMapper;
        this.trainMapper = trainMapper;
        this.scheduleMapper = scheduleMapper;
    }


    @Override
    public void save(StationDto stationDto) {
        stationDao.save(convertStationDtoToEntity(stationDto));
    }

    @Override
    public void deleteById(int id) {
        stationDao.deleteById(id);
    }

    @Override
    public List<TrainDto> findAllTrains(int id) {

        List<Train> allTrains = stationDao.findAllTrainsForCurrentStation(id);
        return allTrains.stream().map(this::toTrainDto).collect(Collectors.toList());

    }

    @Override
    public List<StationDto> findAllDtoStations() {

        return stationDao.findAll().stream().map(this::convertStationToDto).collect(Collectors.toList());

    }

    @Override
    public StationDto findDtoById(int id) {

        return convertStationToDto(stationDao.findById(id));

    }

    @Override
    public List<Schedule> findAllSchedulesForStation(int id) {

        return stationDao.findScheduleForStation(id);
    }

    @Override
    public void saveSchedule2(int stationId, int trainId, LocalTime arrivalTime) {
        stationDao.addSchedule(stationId, trainId, arrivalTime);
    }

    public void saveSchedule(Schedule schedule) {
        stationDao.saveSchedule(schedule);
    }

    @Override
    public List<ScheduleDto> findAllSchedulesDtoForStation(int id) {
        return stationDao.findScheduleForStation(id).stream().map(scheduleMapper::toDto).collect(Collectors.toList());
    }

    private TrainDto toTrainDto(Train train) {
        return trainMapper.toDto(train);
    }

    private StationDto convertStationToDto(Station station) {
        return stationMapper.toDto(station);
    }

    private Station convertStationDtoToEntity(StationDto stationDto) {
        return stationMapper.toEntity(stationDto);
    }

    private ScheduleDto convertScheduleToDto(Schedule schedule) {
        return scheduleMapper.toDto(schedule);
    }

}
