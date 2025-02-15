package ru.tsystems.school.dao;

import ru.tsystems.school.model.Schedule;
import ru.tsystems.school.model.Station;
import ru.tsystems.school.model.Train;

import java.time.LocalTime;
import java.util.List;

public interface StationDao {

    List<Station> findAll();

    Station findById(int id);

    void save(Station station);

    void deleteById(int id);

    List<Train> findAllTrainsForCurrentStation(int id);

    List<Schedule> findScheduleForStation(int id);

    List<Train> findSuitableTrains(Station from, Station to);

    void addSchedule(int stationId, int trainId, LocalTime arrivalTime);

    void saveSchedule(Schedule schedule);
}
