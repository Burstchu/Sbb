package ru.tsystems.school.model;

import lombok.Data;

import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
@Data
public class AbstractPo {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

}
