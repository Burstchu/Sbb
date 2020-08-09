package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.ScheduleDao;
import ru.tsystems.school.dao.StationDao;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.model.Schedule;
import ru.tsystems.school.model.Station;
import ru.tsystems.school.model.StationForTrain;
import ru.tsystems.school.model.Train;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.time.LocalTime;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class StationDaoImpl extends AbstractJpaDao<Station> implements StationDao {

    @PersistenceContext
    private EntityManager entityManager;

    private final ScheduleDao scheduleDao;
    private final TrainDao trainDao;

    public StationDaoImpl(ScheduleDao scheduleDao, TrainDao trainDao) {
        setClazz(Station.class);
        this.trainDao = trainDao;
        this.scheduleDao = scheduleDao;
    }

    @Override
    public List<Train> findAllTrainsForCurrentStation(int id) {

        List<StationForTrain> stationsForTrain =
                entityManager.createQuery(
                        "select s from StationForTrain s where s.station.id =:id", StationForTrain.class)
                        .setParameter("id", id)
                        .getResultList();



        return stationsForTrain.stream().map(StationForTrain::getTrain).collect(Collectors.toList());
    }

    @Override
    public List<Schedule> findScheduleForStation(int id) {

        return entityManager.createQuery(
                "select s from Schedule s where s.station.id=:id", Schedule.class
        ).setParameter("id", id).getResultList();

    }

    @Override
    public void addSchedule(int stationId, int trainId, LocalTime arrivalTime) {

        try {

            Station stationFromDb = findById(stationId);
            if (stationFromDb == null) {
                return;
            }

            Train trainFromDb = trainDao.findById(trainId);
            if (trainFromDb == null) {
                return;
            }

            Schedule schedule = new Schedule(trainFromDb, arrivalTime, stationFromDb);

            scheduleDao.save(schedule);

        } catch (Exception ex) {

        }

    }

    @Override
    public List<Train> findSuitableTrains(Station from, Station to) {

        return null;
    }

    @Override
    public void saveSchedule(Schedule schedule) {
        scheduleDao.save(schedule);
    }
}
