package com.railwaycompany.services.api;

import com.railwaycompany.dto.TicketData;
import com.railwaycompany.dto.UserData;
import com.railwaycompany.exceptions.AlreadyRegisteredException;
import com.railwaycompany.exceptions.HasNoEmptySeatsException;
import com.railwaycompany.exceptions.InvalidInputDataException;
import com.railwaycompany.exceptions.SalesStopException;

import java.util.Date;

// TODO дописать TicketService
public interface TicketService {

    TicketData buyTicket(Long userId, int trainNumber, String stationFromId, String stationToId, Date saleTime,
                         int place, float price, UserData userData)
            throws HasNoEmptySeatsException, AlreadyRegisteredException, SalesStopException, InvalidInputDataException;

    boolean hasEmptySeats(int trainNumber, String stationNameFrom, String stationNameTo, Date dateDeparture);

    boolean hasEnoughTimeBeforeDeparture(int trainNumber, String stationNameFrom, Date dateDeparture);

    boolean isRegistered(int trainNumber, String stationNameFrom, String stationNameTo, Date dateDeparture,
                         String firstName, String lastName, Date birthDate);

    Float getTicketCost(int trainNumber, String stationNameFrom, String stationNameTo, Date date);

}
