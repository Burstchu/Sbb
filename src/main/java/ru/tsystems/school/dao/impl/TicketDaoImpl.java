package ru.tsystems.school.dao.impl;

import org.springframework.stereotype.Repository;
import ru.tsystems.school.dao.AbstractJpaDao;
import ru.tsystems.school.dao.TicketDao;
import ru.tsystems.school.model.Ticket;

@Repository
public class TicketDaoImpl extends AbstractJpaDao<Ticket> implements TicketDao {

    public TicketDaoImpl() {
        super();
        setClazz(Ticket.class);
    }
}
