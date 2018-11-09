package com.railwaycompany.services.api;

import com.railwaycompany.dto.RoutePointDto;
import com.railwaycompany.dto.TicketDto;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.Station;
import com.railwaycompany.entities.Train;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import com.railwaycompany.services.exceptions.CannotBuyTicketException;
import com.railwaycompany.services.exceptions.InvalidInputDataException;

import java.util.Date;
import java.util.List;

public interface TicketService {

    List<TicketDto> buyTicket(User user, Train train, Route route, List<RoutePointDto> routePointDtoList,
                              Station stationFrom, Station stationTo, Date dateDeparture, int carriageNumber, int seatNumber)
            throws AlreadyRegisteredException, CannotBuyTicketException, InvalidInputDataException;

    int[] getPurchasedSeatsByCarriageAndTrainId(long trainId, int carriageNumber);

    List<TicketDto> getAllTicketsByTrainId(long id);

    List<TicketDto> getAllTicketsByTrainNumber(int trainNumber);

    List<TicketDto> getAllTickets();

    boolean hasEnoughTimeBeforeDeparture(int trainNumber, String stationNameFrom, Date dateDeparture) throws InvalidInputDataException;

    boolean isRegistered(User user, int trainNumber, String stationFromName, Date dateDeparture);

    float getTicketCost();

}