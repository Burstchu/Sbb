package ru.tsystems.school.controller;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;
import ru.tsystems.school.model.Schedule;
import ru.tsystems.school.service.StationService;

import java.util.List;

@Controller
@RequestMapping("/stations")
@ControllerAdvice
@SessionAttributes({"stationDto", "schedule"})
public class StationController {

    private final StationService stationService;

    public StationController(StationService stationService) {
        this.stationService = stationService;
    }

    @ModelAttribute("stationDto")
    public StationDto stationDto() {
        return new StationDto();
    }

    @ModelAttribute("schedule")
    public Schedule getSchedule() {
        return new Schedule();
    }

    @GetMapping
    public String getAllStations(Model model) {

        List<StationDto> stationsDto = stationService.findAllDtoStations();

        model.addAttribute("stations", stationsDto);

        return "stationsList";
    }

    @GetMapping("/{id}")
    public String getTrainsForCurrentStation(Model model, @PathVariable int id) {

        StationDto stationDto = stationService.findDtoById(id);

        List<TrainDto> allTrains = stationService.findAllTrains(id);

        List<Schedule> allSchedulesForStation = stationService.findAllSchedulesForStation(id);

        model.addAttribute("station", stationDto);
        model.addAttribute("allTrains", allTrains);
        model.addAttribute("schedule", allSchedulesForStation);

        return "station";

    }

    @PostMapping("/add")
    public String saveStation(@ModelAttribute("stationDto") StationDto stationDto,
                              Model model) {

        model.addAttribute("stationDto", new StationDto());
        stationService.save(stationDto);

        return "redirect:/stations";
    }


    @GetMapping("/add")
    public String addNewTrainPage() {
        return "addNewStation";
    }

}
