package ru.tsystems.school.controller;

import lombok.AllArgsConstructor;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import ru.tsystems.school.dto.StationDto;
import ru.tsystems.school.service.StationService;

import java.util.List;

@Controller
@AllArgsConstructor
public class DefaultController {


    private final StationService stationService;

    @GetMapping
    public String hello() {
        return "home";
    }

    @GetMapping("/error")
    public String error() {
        return "error";
    }

    @GetMapping("/buy_ticket")
    public String buyTicket(Model model) {

        List<StationDto> allDtoStations = stationService.findAllDtoStations();
        model.addAttribute("stations", allDtoStations);

        return "buy_ticket";

    }
}
