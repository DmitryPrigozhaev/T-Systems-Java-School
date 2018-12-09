package com.railwaycompany.controllers;

import com.railwaycompany.dto.TicketDto;
import com.railwaycompany.dto.UserDto;
import com.railwaycompany.services.api.TicketService;
import com.railwaycompany.services.api.UserService;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import com.railwaycompany.services.exceptions.CannotBuyTicketException;
import com.railwaycompany.services.exceptions.InvalidInputDataException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.List;

@Controller
public class BuyTicketController {

    private static final Logger LOG = Logger.getLogger(BuyTicketController.class);

    @Autowired
    UserService userService;

    @Autowired
    TicketService ticketService;

    @PostMapping(value = "buy-ticket-page")
    public String buyTicketPage(@ModelAttribute TicketDto ticketDto, Authentication authentication, ModelMap modelMap) {

        String username = null;
        try {
            username = authentication.getName();
        } catch (Exception e) {
            String message = "Unauthorized user";
            LOG.warn(message);
            return "login";
        }

        UserDto userDto = userService.getUserDtoByEmail(username);
        ticketDto.setUserEmail(userDto.getEmail());

        modelMap.addAttribute("userDto", userDto);
        modelMap.addAttribute("ticketDto", ticketDto);
        return "buy-ticket-page";
    }

    @PostMapping(value = "buy-ticket")
    public String buyTicket(@ModelAttribute UserDto userDto,
                            @ModelAttribute TicketDto ticketDto, ModelMap modelMap) {

        List<TicketDto> ticketDtoList = null;
        try {
            ticketDtoList = ticketService.buyTicket(ticketDto);
            String message = "Ticket purchase was successful";
            modelMap.addAttribute("success", true);
            modelMap.addAttribute("message", message);
        } catch (AlreadyRegisteredException e) {
            String message = "A passenger can have only one ticket";
            modelMap.addAttribute("error", true);
            modelMap.addAttribute("message", message);
            LOG.warn(message);
        } catch (CannotBuyTicketException message) {
            modelMap.addAttribute("error", true);
            modelMap.addAttribute("message", message);
            LOG.warn(message);
        } catch (InvalidInputDataException e) {
            String message = "Invalid input data";
            modelMap.addAttribute("error", true);
            modelMap.addAttribute("message", message);
            LOG.warn(message, e);
        }

        modelMap.addAttribute("success", true);
        modelMap.addAttribute("userDto", userDto);
        modelMap.addAttribute("ticketDto", ticketDto);
        modelMap.addAttribute("ticketList", ticketDtoList);

        return "buy-ticket-page";
    }
}
