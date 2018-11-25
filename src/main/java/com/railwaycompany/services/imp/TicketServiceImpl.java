//package com.railwaycompany.services.imp;
//
//import com.railwaycompany.dao.api.RoutePointDao;
//import com.railwaycompany.dao.api.StationDao;
//import com.railwaycompany.dao.api.TicketDao;
//import com.railwaycompany.dao.api.TrainDao;
//import com.railwaycompany.dto.RoutePointDto;
//import com.railwaycompany.dto.TicketDto;
//import com.railwaycompany.entities.*;
//import com.railwaycompany.services.api.TicketService;
//import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
//import com.railwaycompany.services.exceptions.CannotBuyTicketException;
//import com.railwaycompany.services.exceptions.InvalidInputDataException;
//import com.railwaycompany.utils.DateConverter;
//import org.apache.log4j.Logger;
//import org.springframework.beans.factory.annotation.Autowired;
//import org.springframework.stereotype.Service;
//import org.springframework.transaction.annotation.Propagation;
//import org.springframework.transaction.annotation.Transactional;
//
//import java.util.ArrayList;
//import java.util.Date;
//import java.util.List;
//
//@Service
//@Transactional
//public class TicketServiceImpl implements TicketService {
//
//    private static final Logger LOG = Logger.getLogger(TicketServiceImpl.class.getName());
//
//    @Autowired
//    TicketDao ticketDao;
//
//    @Autowired
//    TrainDao trainDao;
//
//    @Autowired
//    StationDao stationDao;
//
//    @Autowired
//    RoutePointDao routePointDao;
//
//    private TicketDto constructTicketDto(Ticket ticket) {
//        TicketDto ticketDto = new TicketDto();
//        ticketDto.setId(ticket.getId());
//        ticketDto.setUser(ticket.getUser());
//        ticketDto.setTrain(ticket.getTrain());
//        ticketDto.setStationFrom(ticket.getStationFrom());
//        ticketDto.setStationTo(ticket.getStationTo());
//        ticketDto.setSaleTime(ticket.getSaleTime());
//        ticketDto.setCarriage(ticket.getCarriage());
//        ticketDto.setSeat(ticket.getSeat());
//        ticketDto.setPrice(ticket.getPrice());
//        return ticketDto;
//    }
//
//    @Override
//    public List<TicketDto> buyTicket(User user, Train train, Route route, List<RoutePointDto> routePointDtoList,
//                                     Station stationFrom, Station stationTo, Date dateDeparture, int carriageNumber, int seatNumber)
//            throws AlreadyRegisteredException, CannotBuyTicketException {
//
//        // проверка условия "до отправления поезда не менее 10 минут"
//        if (!DateConverter.hasMoreThanTenMinutes(dateDeparture)) {
//            String message = "Less than 10 minutes left until departure";
//            throw new CannotBuyTicketException(message);
//        }
//
//        // определение маршрута пользователя
//        List<RoutePointDto> userRoute = new ArrayList<>();
//        for (int i = 0; i < routePointDtoList.size(); i++) {
//            if (routePointDtoList.get(i).getStationName().getName().equals(stationFrom.getName())) {
//                for (int j = i; j < routePointDtoList.size(); j++) {
//                    userRoute.add(routePointDtoList.get(j));
//                    if (routePointDtoList.get(j).getStationName().getName().equals(stationTo.getName()))
//                        break;
//                }
//                break;
//            }
//        }
//
//        Date saleTime = new Date();
//        List<TicketDto> result = new ArrayList<>();
//
//        // покупка билетов по этапам пути
//        Station stationFromTemp;
//        Station stationToTemp;
//        for (int i = 0; i < userRoute.size() - 1; i++) {
//            stationFromTemp = userRoute.get(i).getStationName();
//            stationToTemp = userRoute.get(i + 1).getStationName();
//            TicketDto ticketPart = buyTicketPart(user, train, stationFromTemp, stationToTemp,
//                    saleTime, dateDeparture, carriageNumber, seatNumber);
//            result.add(ticketPart);
//        }
//
//        return result;
//    }
//
//    @Transactional(propagation = Propagation.MANDATORY)
//    TicketDto buyTicketPart(User user, Train train, Station stationFrom, Station stationTo,
//                            Date saleTime, Date dateDeparture, int carriageNumber, int seatNumber)
//            throws AlreadyRegisteredException, CannotBuyTicketException {
//
//        // проверка наличия места в вагоне
//        int[] purchasedSeats = getPurchasedSeatsByCarriageAndTrainId(train.getId(), carriageNumber);
//        for (int purchasedSeat : purchasedSeats) {
//            if (purchasedSeat == seatNumber) {
//                String message = "Place number " + seatNumber + " in the car number " + carriageNumber + " is already taken";
//                LOG.warn(message);
//                throw new CannotBuyTicketException(message);
//            }
//        }
//
//        // проверка регистрации пользователя на данном этапе маршрута
//        if (isRegistered(user, train.getNumber(), stationFrom.getName(), dateDeparture)) {
//            String message = "The user with id " + user.getId() + " already registered on the route \"" +
//                    stationFrom.getName() + " - " + stationTo.getName() + "\"";
//            LOG.warn(message);
//            throw new AlreadyRegisteredException(message);
//        }
//
//        Ticket ticket = new Ticket();
//        ticket.setUser(user);
//        ticket.setTrain(train);
//        ticket.setStationFrom(stationFrom);
//        ticket.setStationTo(stationTo);
//        ticket.setSaleTime(saleTime);
//        ticket.setCarriage(carriageNumber);
//        ticket.setSeat(seatNumber);
//        ticket.setPrice(getTicketCost());
//        ticket.setDateDeparture(dateDeparture);
//        ticketDao.create(ticket);
//
//        TicketDto ticketDto = new TicketDto();
//        // todo: с id не уверен
//        ticketDto.setId(ticket.getId());
//        ticketDto.setUser(ticket.getUser());
//        ticketDto.setTrain(ticket.getTrain());
//        ticketDto.setStationFrom(ticket.getStationFrom());
//        ticketDto.setStationTo(ticket.getStationTo());
//        ticketDto.setSaleTime(ticket.getSaleTime());
//        ticketDto.setCarriage(ticket.getCarriage());
//        ticketDto.setSeat(ticket.getSeat());
//        ticketDto.setPrice(ticket.getPrice());
//        ticketDto.setDateDeparture(ticket.getDateDeparture());
//
//        return ticketDto;
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public int[] getPurchasedSeatsByCarriageAndTrainId(long trainId, int carriageNumber) {
//        return ticketDao.getPurchasedSeatsByCarriageAndTrainId(trainId, carriageNumber);
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<TicketDto> getAllTicketsByTrainId(long id) {
//        List<TicketDto> ticketDtoList = null;
//        List<Ticket> ticketList = ticketDao.getAllTicketsByTrainId(id);
//        if (ticketList != null && !ticketList.isEmpty()) {
//            ticketDtoList = new ArrayList<>();
//            for (Ticket ticket : ticketList) {
//                ticketDtoList.add(constructTicketDto(ticket));
//            }
//        }
//        return ticketDtoList;
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<TicketDto> getAllTicketsByTrainNumber(int trainNumber) {
//        Train train = trainDao.getTrainByNumber(trainNumber);
//        return getAllTicketsByTrainId(train.getId());
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public List<TicketDto> getAllTickets() {
//        List<TicketDto> ticketDtoList = null;
//        List<Ticket> ticketList = ticketDao.readAll();
//        if (ticketList != null && !ticketList.isEmpty()) {
//            ticketDtoList = new ArrayList<>();
//            for (Ticket ticket : ticketList) {
//                ticketDtoList.add(constructTicketDto(ticket));
//            }
//        }
//        return ticketDtoList;
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public boolean hasEnoughTimeBeforeDeparture(int trainNumber, String stationNameFrom, Date dateDeparture) throws
//            InvalidInputDataException {
//        try {
//            Train train = trainDao.getTrainByNumber(trainNumber);
//            if (train != null) {
//                return DateConverter.hasMoreThanTenMinutes(dateDeparture);
//            } else {
//                String message = "Train with number \"" + trainNumber + "\" does not exist";
//                throw new InvalidInputDataException(message);
//            }
//        } catch (NullPointerException e) {
//            StringBuilder message = new StringBuilder("Input information is not valid: ");
//            if (trainNumber <= 0)
//                message.append("\ntrainNumber = ").append(trainNumber);
//            if (stationNameFrom == null)
//                message.append("\nstationNameFrom = null");
//            if (dateDeparture == null)
//                message.append("\ndateDeparture = null");
//            LOG.warn(message.toString(), e);
//            throw new InvalidInputDataException(message.toString(), e);
//        }
//    }
//
//    @Transactional(readOnly = true)
//    @Override
//    public boolean isRegistered(User user, int trainNumber, String stationFromName, Date dateDeparture) {
//        Train train = trainDao.getTrainByNumber(trainNumber);
//        List<Ticket> ticketList = ticketDao.getTicketByUserIdAndTrainId(user.getId(), train.getId());
//
//        boolean registered = false;
//
//        if (ticketList != null && !ticketList.isEmpty()) {
//            for (Ticket ticket : ticketList) {
//                if (ticket.getStationFrom().getName().equals(stationFromName) &&
//                        ticket.getDateDeparture() == dateDeparture) {
//                    registered = true;
//                }
//            }
//        }
//        return registered;
//    }
//
//    @Override
//    public float getTicketCost() {
//        return 2500.00f;
//    }
//}