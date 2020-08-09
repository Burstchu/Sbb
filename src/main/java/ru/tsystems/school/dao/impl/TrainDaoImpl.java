package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.TrainDao;
import ru.tsystems.school.model.*;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;
import java.util.stream.Collectors;

@Repository
public class TrainDaoImpl extends AbstractJpaDao<Train> implements TrainDao {

    @PersistenceContext
    private EntityManager entityManager;

    public TrainDaoImpl() {
        setClazz(Train.class);
    }

    @Override
    public List<Station> findAllStations(int id) {

        List<StationForTrain> stationsForTrain = entityManager.createQuery(
                "select s from StationForTrain s where s.train.id =:id", StationForTrain.class)
                .setParameter("id", id)
                .getResultList();

        return stationsForTrain.stream().map(StationForTrain::getStation).collect(Collectors.toList());
    }

    @Override
    public List<Passenger> findAllPassengers(int id) {

        List<Ticket> ticketsForCurrentTrain = entityManager.createQuery(
                "select t from Ticket t where t.train.id =:id", Ticket.class)
                .setParameter("id", id)
                .getResultList();

        return ticketsForCurrentTrain.stream().map(Ticket::getPassenger).collect(Collectors.toList());
    }

    @Override
    public List<Train> findByTrainNumber(String number) {

        return entityManager.createQuery(
                "select t from Train t where t.trainNumber like :number", Train.class
        )
                .setParameter("number", "%" + number + "%")
                .getResultList();
    }
}
