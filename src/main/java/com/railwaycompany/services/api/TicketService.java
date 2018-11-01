package com.railwaycompany.services.api;

import com.railwaycompany.dto.TicketDto;
import com.railwaycompany.dto.UserDto;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import com.railwaycompany.services.exceptions.HasNoEmptySeatsException;
import com.railwaycompany.services.exceptions.InvalidInputDataException;
import com.railwaycompany.services.exceptions.SalesStopException;

import java.util.Date;

// TODO дописать TicketService
public interface TicketService {

    TicketDto buyTicket(Long userId, int trainNumber, String stationFromId, String stationToId, Date saleTime,
                        int place, float price, UserDto userDto)
            throws HasNoEmptySeatsException, AlreadyRegisteredException, SalesStopException, InvalidInputDataException;

    boolean hasEmptySeats(int trainNumber, String stationNameFrom, String stationNameTo, Date dateDeparture);

    boolean hasEnoughTimeBeforeDeparture(int trainNumber, String stationNameFrom, Date dateDeparture);

    boolean isRegistered(int trainNumber, String stationNameFrom, String stationNameTo, Date dateDeparture,
                         String firstName, String lastName, Date birthDate);

    Float getTicketCost(int trainNumber, String stationNameFrom, String stationNameTo, Date date);

}
