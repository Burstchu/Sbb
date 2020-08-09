package ru.tsystems.school.service;

import ru.tsystems.school.model.Ticket;

import java.util.List;

public interface TicketService {

    List<Ticket> findAll();

    Ticket findById(int id);

    void save(Ticket ticket);

    void deleteById(int id);

}
