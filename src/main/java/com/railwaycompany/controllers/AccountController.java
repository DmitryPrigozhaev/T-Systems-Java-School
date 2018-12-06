package com.railwaycompany.controllers;

import com.railwaycompany.dto.TicketDto;
import com.railwaycompany.dto.UserDto;
import com.railwaycompany.services.api.TicketService;
import com.railwaycompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import java.util.List;

@Controller
public class AccountController {

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    @RequestMapping(value = "account", method = {RequestMethod.GET, RequestMethod.POST})
    public String account(ModelMap modelMap) {
        if (SecurityContextHolder.getContext().getAuthentication() != null &&
                SecurityContextHolder.getContext().getAuthentication().isAuthenticated()) {

            UserDto userDto = userService.getUserDtoByEmail(SecurityContextHolder.getContext().getAuthentication().getName());
            List<TicketDto> ticketDtoList = ticketService.getTicketDtoByUserDto(userDto);

            for (TicketDto ticketDto : ticketDtoList) {
                System.out.println("_____________________________________________");
                System.out.println("ticketDto.getUserEmail() = " + ticketDto.getUserEmail());
                System.out.println("ticketDto.getRouteName() = " + ticketDto.getRouteName());
                System.out.println("ticketDto.getTrainNumber() = " + ticketDto.getTrainNumber());
                System.out.println("ticketDto.getCarriageNumber() = " + ticketDto.getCarriageNumber());
                System.out.println("ticketDto.getSeatNumber() = " + ticketDto.getSeatNumber());
                System.out.println("ticketDto.getStationFromName() = " + ticketDto.getStationFromName());
                System.out.println("ticketDto.getStationToName() = " + ticketDto.getStationToName());
                System.out.println("ticketDto.getSaleTime() = " + ticketDto.getSaleTime());
                System.out.println("ticketDto.getPrice() = " + ticketDto.getPrice());
                System.out.println("_____________________________________________");
            }

            modelMap.addAttribute("userDto", userDto);
            modelMap.addAttribute("ticketDtoList", ticketDtoList);
        }
        return "account";
    }

}
