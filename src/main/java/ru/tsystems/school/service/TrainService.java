package ru.tsystems.school.service;

import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;

import java.util.List;

public interface TrainService {

    List<TrainDto> findAllDtoTrains();

    TrainDto findTrainDtoById(int id);

    void save(TrainDto trainDto);

    void deleteById(int id);

    List<StationDto> findAllStations(int id);

    List<PassengerDto> findAllPassengers(int id);

    List<TrainDto> findTrainDtoByNumber(String number);

    void update(TrainDto trainDto);

}
