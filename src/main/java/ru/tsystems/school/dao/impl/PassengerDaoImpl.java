package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.PassengerDao;
import ru.tsystems.school.model.Passenger;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Repository
public class PassengerDaoImpl extends AbstractJpaDao<Passenger> implements PassengerDao {

    @PersistenceContext
    EntityManager entityManager;

    public PassengerDaoImpl() {
        super();
        setClazz(Passenger.class);
    }

    @Override
    public Passenger findByUserName(String userName) {

        Passenger passenger = null;

        try {
            passenger = entityManager.createQuery("select p from Passenger p where p.userName=:userName",
                    Passenger.class)
                    .setParameter("userName", userName)
                    .getSingleResult();
        } catch (Exception exception) {

        }

        return passenger;

    }
}
