package ru.tsystems.school.service;

import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.model.Passenger;

import java.util.List;

public interface PassengerService {

    List<PassengerDto> findAllDtoPassengers();

    Passenger findById(int id);

    PassengerDto findPassengerDtoById(int id);

    void save(PassengerDto passengerDto);

    void delete(int id);

    void createUser(Passenger passenger);

}
