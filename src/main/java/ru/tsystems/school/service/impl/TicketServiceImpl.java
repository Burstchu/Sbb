package ru.tsystems.school.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import ru.tsystems.school.dao.TicketDao;
import ru.tsystems.school.model.Ticket;
import ru.tsystems.school.service.TicketService;

import java.util.List;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private final TicketDao ticketDao;


    public TicketServiceImpl(TicketDao ticketDao) {
        this.ticketDao = ticketDao;
    }

    @Override

    public List<Ticket> findAll() {
        return ticketDao.findAll();
    }

    @Override
    public Ticket findById(int id) {
        return null;
    }

    @Override
    public void save(Ticket ticket) {

    }

    @Override
    public void deleteById(int id) {

    }
}
