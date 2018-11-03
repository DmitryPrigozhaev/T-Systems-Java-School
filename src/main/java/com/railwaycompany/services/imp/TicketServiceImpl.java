package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RoutePointDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.dao.api.TicketDao;
import com.railwaycompany.dao.api.TrainDao;
import com.railwaycompany.dto.TicketDto;
import com.railwaycompany.entities.Station;
import com.railwaycompany.entities.Ticket;
import com.railwaycompany.entities.Train;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.api.TicketService;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import com.railwaycompany.services.exceptions.CannotBuyTicketException;
import com.railwaycompany.services.exceptions.InvalidInputDataException;
import com.railwaycompany.utils.DateConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

public class TicketServiceImpl implements TicketService {

    private static final Logger LOG = Logger.getLogger(TicketServiceImpl.class.getName());

    @Autowired
    TicketDao ticketDao;

    @Autowired
    TrainDao trainDao;

    @Autowired
    StationDao stationDao;

    @Autowired
    RoutePointDao routePointDao;

    private TicketDto constructTicketDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setId(ticket.getId());
        ticketDto.setUser(ticket.getUser());
        ticketDto.setTrain(ticket.getTrain());
        ticketDto.setStationFrom(ticket.getStationFrom());
        ticketDto.setStationTo(ticket.getStationTo());
        ticketDto.setSaleTime(ticket.getSaleTime());
        ticketDto.setCarriage(ticket.getCarriage());
        ticketDto.setSeat(ticket.getSeat());
        ticketDto.setPrice(ticket.getPrice());
        return ticketDto;
    }

    // TODO дописать сервис
    @Override
    public TicketDto buyTicket(User user, Train train, int carriageNumber, int seatNumber, String stationFromId,
                               String stationToId, Date saleTime, Date dateArrival, Date dateDeparture, float price)
            throws AlreadyRegisteredException, CannotBuyTicketException {
        return null;
    }

    // TODO дописать сервис
    private TicketDto buyTicketPart(User user, Train train, Station stationFrom, Station stationTo,
                                    Date saleTime, int carriageNumber, int seatNumber, float price, Date dateDeparture)
            throws AlreadyRegisteredException, CannotBuyTicketException {

        int[] purchasedSeats = getPurchasedSeatsByCarriageAndTrainId(train.getId(), carriageNumber);
        for (int purchasedSeat : purchasedSeats) {
            if (purchasedSeat == seatNumber) {
                String message = "Place number " + seatNumber + " in the car number " + carriageNumber + " is already taken";
                throw new CannotBuyTicketException(message);
            }
        }

        if (isRegistered(user, train.getNumber(), stationFrom.getName(), dateDeparture)) {
            String message = "The user with id " + user.getId() + " already registered";
            throw new AlreadyRegisteredException(message);
        }

        return null;
    }

    @Override
    public int[] getPurchasedSeatsByCarriageAndTrainId(long trainId, int carriageNumber) {
        return ticketDao.getPurchasedSeatsByCarriageAndTrainId(trainId, carriageNumber);
    }

    @Override
    public List<TicketDto> getAllTicketsByTrainId(long id) {
        List<TicketDto> ticketDtoList = null;
        List<Ticket> ticketList = ticketDao.getAllTicketsByTrainId(id);
        if (ticketList != null && !ticketList.isEmpty()) {
            ticketDtoList = new ArrayList<>();
            for (Ticket ticket : ticketList) {
                ticketDtoList.add(constructTicketDto(ticket));
            }
        }
        return ticketDtoList;
    }

    @Override
    public List<TicketDto> getAllTicketsByTrainNumber(int trainNumber) {
        Train train = trainDao.getTrainByNumber(trainNumber);
        return getAllTicketsByTrainId(train.getId());
    }

    @Override
    public List<TicketDto> getAllTickets() {
        List<TicketDto> ticketDtoList = null;
        List<Ticket> ticketList = ticketDao.readAll();
        if (ticketList != null && !ticketList.isEmpty()) {
            ticketDtoList = new ArrayList<>();
            for (Ticket ticket : ticketList) {
                ticketDtoList.add(constructTicketDto(ticket));
            }
        }
        return ticketDtoList;
    }

    @Override
    public boolean hasEnoughTimeBeforeDeparture(int trainNumber, String stationNameFrom, Date dateDeparture) throws
            InvalidInputDataException {
        try {
            Train train = trainDao.getTrainByNumber(trainNumber);
            if (train != null) {
                return DateConverter.hasMoreThanTenMinutes(dateDeparture);
            } else {
                String message = "Train with number \"" + trainNumber + "\" does not exist";
                throw new InvalidInputDataException(message);
            }
        } catch (NullPointerException e) {
            StringBuilder message = new StringBuilder("Input information is not valid: ");
            if (trainNumber <= 0)
                message.append("\ntrainNumber = ").append(trainNumber);
            if (stationNameFrom == null)
                message.append("\nstationNameFrom = null");
            if (dateDeparture == null)
                message.append("\ndateDeparture = null");
            LOG.warn(message.toString(), e);
            throw new InvalidInputDataException(message.toString(), e);
        }
    }

    @Override
    public boolean isRegistered(User user, int trainNumber, String stationFromName, Date dateDeparture) {
        Train train = trainDao.getTrainByNumber(trainNumber);
        List<Ticket> ticketList = ticketDao.getTicketByUserIdAndTrainId(user.getId(), train.getId());

        boolean registered = false;

        if (ticketList != null && !ticketList.isEmpty()) {
            for (Ticket ticket : ticketList) {
                if (ticket.getStationFrom().getName().equals(stationFromName) &&
                        ticket.getDateDeparture() == dateDeparture) {
                    registered = true;
                }
            }
        }
        return registered;
    }

    @Override
    public float getTicketCost(int trainNumber, String stationNameFrom, String stationNameTo) {
        return 2500.00f;
    }
}