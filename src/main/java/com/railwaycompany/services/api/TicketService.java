package com.railwaycompany.services.api;

import com.railwaycompany.dto.TicketDto;
import com.railwaycompany.entities.Train;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import com.railwaycompany.services.exceptions.CannotBuyTicketException;
import com.railwaycompany.services.exceptions.InvalidInputDataException;

import java.util.Date;
import java.util.List;

public interface TicketService {

    TicketDto buyTicket(User user, Train train, int carriageNumber, int seatNumber,
                        String stationFromId, String stationToId,
                        Date saleTime, Date dateArrival, Date dateDeparture, float price)
            throws AlreadyRegisteredException, CannotBuyTicketException, InvalidInputDataException;

    int[] getPurchasedSeatsByCarriageAndTrainId(long trainId, int carriageNumber);

    List<TicketDto> getAllTicketsByTrainId(long id);

    List<TicketDto> getAllTicketsByTrainNumber(int trainNumber);

    List<TicketDto> getAllTickets();

    boolean hasEnoughTimeBeforeDeparture(int trainNumber, String stationNameFrom, Date dateDeparture) throws InvalidInputDataException;

    boolean isRegistered(User user, int trainNumber, String stationFromName, Date dateDeparture);

    float getTicketCost(int trainNumber, String stationNameFrom, String stationNameTo);

}