package ru.tsystems.school.service.impl;

import lombok.extern.log4j.Log4j;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;
import ru.tsystems.school.mapper.PassengerMapper;
import ru.tsystems.school.mapper.StationMapper;
import ru.tsystems.school.mapper.TrainMapper;
import ru.tsystems.school.model.Train;
import ru.tsystems.school.service.TrainService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
@Log4j
public class TrainServiceImpl implements TrainService {

    private final TrainDao trainDao;
    private final PassengerMapper passengerMapper;
    private final StationMapper stationMapper;
    private final TrainMapper trainMapper;

    public TrainServiceImpl(TrainDao trainDao, TrainMapper trainMapper,
                            StationMapper stationMapper, PassengerMapper passengerMapper) {
        this.trainDao = trainDao;
        this.trainMapper = trainMapper;
        this.stationMapper = stationMapper;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public List<TrainDto> findAllDtoTrains() {

        return trainDao.findAll()
                .stream()
                .map(this::convertTrainToDto)
                .collect(Collectors.toList());
    }

    @Override
    public TrainDto findTrainDtoById(int id) {
        return trainMapper.toDto(trainDao.findById(id));
    }

    @Override
    public void save(TrainDto trainDto) {
        Train train = convertTrainToEntity(trainDto);
        trainDao.save(train);
    }

    @Override
    public void deleteById(int id) {
        trainDao.deleteById(id);
    }

    @Override
    public List<StationDto> findAllStations(int id) {
        return trainDao.findAllStations(id)
                .stream()
                .map(stationMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<PassengerDto> findAllPassengers(int id) {
        return trainDao.findAllPassengers(id)
                .stream()
                .map(passengerMapper::toDto)
                .collect(Collectors.toList());
    }

    @Override
    public List<TrainDto> findTrainDtoByNumber(String number) {

        if (number == null) {
            return null;
        }

        return trainDao.findByTrainNumber(number)
                .stream()
                .map(this::convertTrainToDto)
                .collect(Collectors.toList());

    }

    @Override
    public void update(TrainDto trainDto) {
        log.info("train has: " + trainDto.getTrainNumber() + " number before");
        Train train = trainMapper.toEntity(trainDto);
        trainDao.update(train);
        log.info("now it has: " + train.getTrainNumber());

    }

    private TrainDto convertTrainToDto(Train train) {
        return trainMapper.toDto(train);
    }

    private Train convertTrainToEntity(TrainDto trainDto) {
        return trainMapper.toEntity(trainDto);
    }

}
