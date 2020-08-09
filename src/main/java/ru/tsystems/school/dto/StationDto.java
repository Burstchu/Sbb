package ru.tsystems.school.dto;

import lombok.*;

import java.util.List;

@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
@Builder
@ToString
public class StationDto {

    private int id;
    private String name;
    private List<TrainDto> trains;
    private List<ScheduleDto> schedules;

}
