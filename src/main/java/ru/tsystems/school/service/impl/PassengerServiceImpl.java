package ru.tsystems.school.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.PassengerDao;
import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.mapper.PassengerMapper;
import ru.tsystems.school.model.Passenger;
import ru.tsystems.school.service.PassengerService;

import java.util.List;
import java.util.stream.Collectors;

@Service
@Transactional
public class PassengerServiceImpl implements PassengerService {

    private final PassengerDao passengerDao;

    private final PassengerMapper passengerMapper;

    public PassengerServiceImpl(PassengerDao passengerDao, PassengerMapper passengerMapper) {
        this.passengerDao = passengerDao;
        this.passengerMapper = passengerMapper;
    }

    @Override
    public Passenger findById(int id) {
        return passengerDao.findById(id);
    }


    @Override
    public void delete(int id) {
        passengerDao.deleteById(id);
    }

    @Override
    public List<PassengerDto> findAllDtoPassengers() {
        return passengerDao.findAll().stream().map(this::convertPassengerToDto).collect(Collectors.toList());
    }

    @Override
    public void save(PassengerDto passengerDto) {

        Passenger passenger = convertPassengerToEntity(passengerDto);
        passengerDao.save(passenger);

    }

    @Override
    public void createUser(Passenger passenger) {
        passengerDao.save(passenger);
    }

    @Override
    public PassengerDto findPassengerDtoById(int id) {
        return convertPassengerToDto(passengerDao.findById(id));
    }

    private PassengerDto convertPassengerToDto(Passenger passenger) {
        return passengerMapper.toDto(passenger);
    }

    private Passenger convertPassengerToEntity(PassengerDto passengerDto) {
        return passengerMapper.toEntity(passengerDto);
    }

}
