package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.*;
import com.railwaycompany.dto.TicketDto;
import com.railwaycompany.dto.UserDto;
import com.railwaycompany.entities.*;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.TicketService;
import com.railwaycompany.services.api.UserService;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import com.railwaycompany.services.exceptions.CannotBuyTicketException;
import com.railwaycompany.services.exceptions.InvalidInputDataException;
import com.railwaycompany.utils.DateConverter;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Propagation;
import org.springframework.transaction.annotation.Transactional;

import java.util.*;

@Service
@Transactional
public class TicketServiceImpl implements TicketService {

    private static final Logger LOG = Logger.getLogger(TicketServiceImpl.class.getName());

    @Autowired
    UserDao userDao;

    @Autowired
    UserService userService;

    @Autowired
    TicketDao ticketDao;

    @Autowired
    TrainDao trainDao;

    @Autowired
    StationDao stationDao;

    @Autowired
    RouteDao routeDao;

    @Autowired
    RoutePointDao routePointDao;

    private TicketDto constructTicketDto(Ticket ticket) {
        TicketDto ticketDto = new TicketDto();
        ticketDto.setUserEmail(ticket.getUser().getEmail());
        ticketDto.setTrainNumber(ticket.getTrain().getNumber());
        ticketDto.setCarriageNumber(ticket.getCarriage());
        ticketDto.setSeatNumber(ticket.getSeat());
        ticketDto.setRouteName(ticket.getRoute().getName());
        ticketDto.setStationFromName(ticket.getStationFrom().getName());
        ticketDto.setStationToName(ticket.getStationTo().getName());
        ticketDto.setSaleTime(ticket.getSaleTime().toString());
        ticketDto.setPrice(ticket.getPrice());
        return ticketDto;
    }

    @Override
    public List<TicketDto> buyTicket(TicketDto ticketDto) throws AlreadyRegisteredException, CannotBuyTicketException, InvalidInputDataException {

        // user route definition
        List<RoutePoint> userRoute = null;
        Route route = routeDao.getRouteByName(ticketDto.getRouteName());
        if (route != null) {
            userRoute = new ArrayList<>();
            List<RoutePoint> routePointList = route.getRoutePointList();
            Collections.sort(routePointList);
            if (!routePointList.isEmpty()) {
                for (int i = 0; i < routePointList.size(); i++) {
                    if (routePointList.get(i).getStation().getName().equals(ticketDto.getStationFromName())) {
                        for (int j = i; j < routePointList.size(); j++) {
                            userRoute.add(routePointList.get(j));
                            if (routePointList.get(j).getStation().getName().equals(ticketDto.getStationToName()))
                                break;
                        }
                        break;
                    }
                }
            }
        } else {
            String message = "Invalid input data: route with name \"" + ticketDto.getRouteName() + "\" does not exist";
            throw new InvalidInputDataException(message);
        }

        // check the condition "before the departure of the train at least 10 minutes"
        if (!DateConverter.hasMoreThanTenMinutes(userRoute.get(0).getDateDeparture())) {
            String message = "Less than 10 minutes left until departure";
            throw new CannotBuyTicketException(message);
        }

        Date saleTime = new Date();
        List<TicketDto> result = new ArrayList<>();

        // purchase part of the ticket
        Station stationFromTemp;
        Station stationToTemp;
        Date dateDeparture;
        for (int i = 0; i < userRoute.size() - 1; i++) {
            stationFromTemp = stationDao.getStationByName(userRoute.get(i).getStation().getName());
            stationToTemp = stationDao.getStationByName(userRoute.get(i + 1).getStation().getName());
            dateDeparture = userRoute.get(i).getDateDeparture();
            TicketDto ticketPart = buyTicketPart(ticketDto, stationFromTemp, stationToTemp, saleTime, dateDeparture);
            result.add(ticketPart);
        }

        return result;
    }

    @Transactional(propagation = Propagation.MANDATORY)
    TicketDto buyTicketPart(TicketDto ticketDto, Station stationFrom, Station stationTo, Date saleTime, Date dateDeparture)
            throws AlreadyRegisteredException, CannotBuyTicketException {

        // checking the availability of free space in the carriage
        Train train = trainDao.getTrainByNumber(ticketDto.getTrainNumber());
        List<Ticket> carriageTicketList = ticketDao.getTicketsByTrainIdAndCarriageNumber(train.getId(), ticketDto.getCarriageNumber());
        for (Ticket ticket : carriageTicketList) {
            if (ticketDto.getSeatNumber().equals(ticket.getSeat()) &&
                    ticket.getStationFrom().getName().equals(stationFrom.getName())) {
                String message = "Place number " + ticketDto.getSeatNumber() +
                        " in the carriage number " + ticketDto.getCarriageNumber() +
                        " is already taken";
                LOG.warn(message);
                throw new CannotBuyTicketException(message);
            }
        }

        // check user registration at this stage of the route
        User user = userDao.getUserByEmail(ticketDto.getUserEmail());
        if (isRegistered(user, ticketDto.getTrainNumber(), stationFrom.getName(), dateDeparture)) {
            String message = "The user with email " + user.getEmail() +
                    " already registered on the route \"" + stationFrom.getName() + " - " + stationTo.getName() + "\"";
            LOG.warn(message);
            throw new AlreadyRegisteredException(message);
        }

        Route route = routeDao.getRouteByName(ticketDto.getRouteName());

        Ticket ticket = new Ticket();
        ticket.setUser(user);
        ticket.setTrain(train);
        ticket.setRoute(route);
        ticket.setStationFrom(stationFrom);
        ticket.setStationTo(stationTo);
        ticket.setSaleTime(saleTime);
        ticket.setCarriage(ticketDto.getCarriageNumber());
        ticket.setSeat(ticketDto.getSeatNumber());
        ticket.setPrice(getTicketCost());
        ticketDao.create(ticket);

        return constructTicketDto(ticket);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TicketDto> getTicketDtoByUserDto(UserDto userDto) {
        List<TicketDto> ticketDtoList = null;
        List<Ticket> ticketList = ticketDao.getTicketByUserEmail(userDto.getEmail());
        if (ticketList != null && !ticketList.isEmpty()) {
            ticketDtoList = new ArrayList<>();

            List<Ticket> temp = new ArrayList<>();
            float price = 0f;

            Set<Date> dateTimeSet = new HashSet<>();
            for (Ticket ticket : ticketList) {
                dateTimeSet.add(ticket.getSaleTime());
            }
            for (Date date : dateTimeSet) {
                for (Ticket ticket : ticketList) {
                    if (ticket.getSaleTime().equals(date)) {
                        temp.add(ticket);
                        price += ticket.getPrice();
                    }
                }

                String datetimeDeparture = "";
                String datetimeArrival = "";
                List<RoutePoint> routePointList = temp.get(0).getRoute().getRoutePointList();
                for (RoutePoint routePoint : routePointList) {
                    if (routePoint.getStation().equals(temp.get(0).getStationFrom()))
                        datetimeDeparture = routePoint.getDateDeparture().toString();
                    if (routePoint.getStation().equals(temp.get(0).getStationTo()))
                        datetimeArrival = routePoint.getDateArrival().toString();
                }

                TicketDto ticketDto = new TicketDto();
                ticketDto.setUserEmail(temp.get(0).getUser().getEmail());
                ticketDto.setRouteName(temp.get(0).getRoute().getName());
                ticketDto.setTrainNumber(temp.get(0).getTrain().getNumber());
                ticketDto.setCarriageNumber(temp.get(0).getCarriage());
                ticketDto.setSeatNumber(temp.get(0).getSeat());
                ticketDto.setSaleTime(temp.get(0).getSaleTime().toString());
                ticketDto.setStationFromName(temp.get(0).getStationFrom().getName());
                ticketDto.setStationToName(temp.get(temp.size()-1).getStationTo().getName());
                ticketDto.setDatetimeDeparture(datetimeDeparture);
                ticketDto.setDatetimeArrival(datetimeArrival);
                ticketDto.setPrice(price);

                ticketDtoList.add(ticketDto);

                temp.clear();
                price = 0f;
            }
        }
        return ticketDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TicketDto> getAllTicketsByTrainNumberAndCarriage(int trainNumber, int carriageNumber) {
        List<TicketDto> ticketDtoList = null;
        Train train = trainDao.getTrainByNumber(trainNumber);
        List<Ticket> ticketList = ticketDao.getTicketsByTrainIdAndCarriageNumber(train.getId(), carriageNumber);
        if (ticketList != null && !ticketList.isEmpty()) {
            ticketDtoList = new ArrayList<>();
            for (Ticket ticket : ticketList) {
                ticketDtoList.add(constructTicketDto(ticket));
            }
        }
        return ticketDtoList;
    }

    // метод возвращает все билеты на вагон по указанному отрезку
    @Transactional(readOnly = true)
    @Override
    public List<TicketDto> getAllTicketsByTrainNumberAndCarriageAndStations(int trainNumber, int carriageNumber,
                                                                            String stationFromName, String stationToName) {

        List<TicketDto> result = null;

        Route route = routeDao.getRouteByTrainNumber(trainNumber);
        List<RoutePoint> routePointList = route.getRoutePointList();
        if (routePointList != null && !routePointList.isEmpty()) {

            Collections.sort(routePointList);

            List<String> userStationList = new ArrayList<>();
            for (int i = 0; i < routePointList.size(); i++) {
                if (routePointList.get(i).getStation().getName().equals(stationFromName)) {
                    for (int j = i; j < routePointList.size(); j++) {
                        userStationList.add(routePointList.get(j).getStation().getName());
                        if (routePointList.get(j).getStation().getName().equals(stationToName)) {
                            break;
                        }
                    }
                    break;
                }
            }
            userStationList.remove(userStationList.size() - 1);

            List<TicketDto> ticketDtoList = getAllTicketsByTrainNumberAndCarriage(trainNumber, carriageNumber);
            if (ticketDtoList != null && !ticketDtoList.isEmpty()) {
                result = new ArrayList<>();
                for (TicketDto ticketDto : ticketDtoList) {
                    if (userStationList.contains(ticketDto.getStationFromName())) {
                        result.add(ticketDto);
                    }
                }
            }
        } else {
            String message = "Route points for route \"" + route.getName() + "\" does not exist";
            LOG.warn(message);
        }
        return result;
    }

    @Transactional(readOnly = true)
    @Override
    public List<TicketDto> getAllTicketsByTrainNumber(int trainNumber) {
        List<TicketDto> ticketDtoList = null;
        Train train = trainDao.getTrainByNumber(trainNumber);
        List<Ticket> ticketList = ticketDao.getAllTicketsByTrainId(train.getId());
        if (ticketList != null && !ticketList.isEmpty()) {
            ticketDtoList = new ArrayList<>();
            for (Ticket ticket : ticketList) {
                ticketDtoList.add(constructTicketDto(ticket));
            }
        }
        return ticketDtoList;
    }

    @Override
    public List<UserDto> getAllUsersDtoByTrainNumber(int trainNumber) {
        List<UserDto> userDtoList = null;
        List<TicketDto> ticketDtoList = getAllTicketsByTrainNumber(trainNumber);
        if (ticketDtoList != null && !ticketDtoList.isEmpty()) {
            userDtoList = new ArrayList<>();
            Set<String> emailSet = new HashSet<>();
            for (TicketDto ticketDto : ticketDtoList) {
                emailSet.add(ticketDto.getUserEmail());
            }
            for (String email : emailSet) {
                UserDto userDto = userService.getUserDtoByEmail(email);
                userDtoList.add(userDto);
            }
        }

        return userDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public boolean isRegistered(User user, int trainNumber, String stationFromName, Date dateDeparture) {
        Train train = trainDao.getTrainByNumber(trainNumber);
        List<Ticket> ticketList = ticketDao.getTicketByUserIdAndTrainId(user.getId(), train.getId());

        boolean registered = false;

        if (ticketList != null && !ticketList.isEmpty()) {
            for (Ticket ticket : ticketList) {
                if (ticket.getUser().getEmail().equals(user.getEmail()) &&
                        ticket.getTrain().getNumber().equals(trainNumber) &&
                        ticket.getStationFrom().getName().equals(stationFromName)) {
                    registered = true;
                }
            }
        }
        return registered;
    }

    @Override
    public float getTicketCost() {
        return 250.00f;
    }
}