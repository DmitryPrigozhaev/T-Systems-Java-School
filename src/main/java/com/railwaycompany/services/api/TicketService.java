package com.railwaycompany.services.api;

import com.railwaycompany.dto.TicketDto;
import com.railwaycompany.dto.UserDto;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import com.railwaycompany.services.exceptions.CannotBuyTicketException;
import com.railwaycompany.services.exceptions.InvalidInputDataException;

import java.util.Date;
import java.util.List;

public interface TicketService {

    List<TicketDto> buyTicket(TicketDto ticketDto) throws AlreadyRegisteredException, CannotBuyTicketException, InvalidInputDataException;

    List<TicketDto> getTicketDtoByUserDto(UserDto userDto);

    List<TicketDto> getAllTicketsByTrainNumberAndCarriage(int trainNumber, int carriageNumber);

    List<TicketDto> getAllTicketsByTrainNumberAndCarriageAndStations(int trainNumber, int carriageNumber, String stationFromName, String stationToName);

    List<TicketDto> getAllTicketsByTrainNumber(int trainNumber);

    List<UserDto> getAllUsersDtoByTrainNumber(int trainNumber);

    boolean isRegistered(User user, int trainNumber, String stationFromName, Date dateDeparture);

    float getTicketCost();

}