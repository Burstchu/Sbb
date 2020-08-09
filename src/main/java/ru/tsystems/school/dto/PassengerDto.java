package ru.tsystems.school.dto;

import lombok.Builder;
import lombok.Data;

import java.time.LocalDate;

@Data
@Builder
public class PassengerDto {

    private int id;
    private String firstName;
    private String lastName;
    private String birthDate;

}
