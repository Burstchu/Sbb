package ru.tsystems.school.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class TrainDto {

    private int id;
    private String trainNumber;
    private int seatsAmount;
    private List<StationDto> stations;
    private List<PassengerDto> passengers;
    private List<ScheduleDto> schedules;

}
