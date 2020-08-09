package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.ScheduleDao;
import ru.tsystems.school.model.Schedule;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.util.List;

@Repository
public class ScheduleDaoImpl extends AbstractJpaDao<Schedule> implements ScheduleDao {


    @PersistenceContext
    private EntityManager entityManager;

    public ScheduleDaoImpl() {
        setClazz(Schedule.class);
    }

    @Override
    @Transactional
    public List<Schedule> findAll() {
        List<Schedule> list = entityManager.createQuery("select s from Schedule s", Schedule.class).getResultList();


//        System.out.println("hello from dao impl");
//        for (Schedule schedule : list) {
//            System.out.println(schedule.getTrain().getTrainNumber() + " arrives at " + schedule.getArrivalTime()
//            + " at station " + schedule.getStation().getName());
//        }

        return list;
    }
}
