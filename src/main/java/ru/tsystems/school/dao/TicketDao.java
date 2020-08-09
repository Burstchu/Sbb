package ru.tsystems.school.dao;

import ru.tsystems.school.model.Ticket;

import java.util.List;

public interface TicketDao {

    List<Ticket> findAll();

    Ticket findById(int id);

    void save(Ticket ticket);

    void deleteById(int id);


}
