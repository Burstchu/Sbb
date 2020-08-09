package ru.tsystems.school.controller;

import lombok.AllArgsConstructor;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import ru.tsystems.school.dto.PassengerDto;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.dto.TrainDto;
import ru.tsystems.school.service.TrainService;

import java.util.ArrayList;
import java.util.List;

@Controller
@RequestMapping("/trains")
@ControllerAdvice
@SessionAttributes({"trainDto", "trains", "stationDto"})
@AllArgsConstructor
public class TrainController {

    private final TrainService trainService;

    @ModelAttribute("stationDto")
    public StationDto getStationDto() {
        return new StationDto();
    }

    @ModelAttribute("trainDto")
    public TrainDto getTrainDto() {
        return new TrainDto();
    }

    @ModelAttribute("trainsDto")
    public List<TrainDto> getTrainsDto() {
        return new ArrayList<>();
    }

    @GetMapping
    @PreAuthorize("hasAuthority('ADMIN')")
    public String getAllTrains(Model model) {

        List<TrainDto> trainsDto = trainService.findAllDtoTrains();

        model.addAttribute("trains", trainsDto);

        return "trainsList";
    }

    @GetMapping("/{id}")
    public String findTrainById(@PathVariable int id, Model model) {

        List<StationDto> stationsDto = trainService.findAllStations(id);
        List<PassengerDto> passengersDto = trainService.findAllPassengers(id);
        TrainDto trainDtoById = trainService.findTrainDtoById(id);

        model.addAttribute("train", trainDtoById);
        model.addAttribute("allStations", stationsDto);
        model.addAttribute("passengers", passengersDto);

        return "train";
    }

    @PostMapping("/add")
    public String saveTrain(@ModelAttribute("trainDto") TrainDto train,
                            Model model) {

        model.addAttribute("trainDto", new TrainDto());
        trainService.save(train);

        return "redirect:/trains";
    }

    @PostMapping("/filter")
    public String getByNumber(@RequestParam String number,
                              @ModelAttribute("trainDto") TrainDto trainDto, Model model) {

        List<TrainDto> trains = trainService.findTrainDtoByNumber(number);

        model.addAttribute("number", number);
        model.addAttribute("trains", trains);

        return "home";
    }

    @GetMapping("/add")
    public String addNewTrainPage() {
        return "addNewTrain";
    }

    @GetMapping("/edit/{id}")
    public String updateTrain(@PathVariable int id, Model model) {
        TrainDto trainDtoById = trainService.findTrainDtoById(id);
        model.addAttribute("train", trainDtoById);
        return "editTrain";
    }

    @PostMapping("/update")
    public String editTrain(@ModelAttribute("trainDto") TrainDto train) {
        trainService.update(train);
        return "redirect:/trains";
    }


    @GetMapping("delete/{id}")
    public String deleteTrain(@PathVariable int id) {
        trainService.deleteById(id);
        return "redirect:/trains";

    }
}


