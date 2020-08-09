package ru.tsystems.school.dto;

import lombok.*;

import java.time.LocalTime;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class ScheduleDto {

    private int id;
    private TrainDto train;
    private LocalTime arrivalTime;
    private StationDto station;

}
