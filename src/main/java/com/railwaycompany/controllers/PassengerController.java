package com.railwaycompany.controllers;

import com.railwaycompany.dto.TicketDto;
import com.railwaycompany.dto.TrainDto;
import com.railwaycompany.services.api.TicketService;
import com.railwaycompany.services.api.TrainService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;

@Controller
public class PassengerController {

    @Autowired
    TrainService trainService;

    @Autowired
    TicketService ticketService;

    @GetMapping(value = "admin-all-passengers")
    public String passengerPage(ModelMap modelMap) {

        List<TrainDto> trainDtoList = trainService.getAllTrains();

        modelMap.addAttribute("trainDtoList", trainDtoList);
        return "admin-all-passengers";
    }

    @ResponseBody
    @PostMapping(value = "get-tickets", consumes = "application/json")
    public List<TicketDto> getTickets(@RequestBody TrainDto trainDto) {

        System.out.println("trainDto.getId() = " + trainDto.getId());
        System.out.println("trainDto.getNumber() = " + trainDto.getNumber());
        System.out.println("trainDto.getNumberOfCarriages() = " + trainDto.getNumberOfCarriages());
        System.out.println("trainDto.getRouteName() = " + trainDto.getRouteName());

        return ticketService.getAllTicketsByTrainNumber(trainDto.getNumber());
    }

}