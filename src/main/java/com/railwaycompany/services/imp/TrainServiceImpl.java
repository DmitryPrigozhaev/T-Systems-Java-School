package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RouteDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.dao.api.TrainDao;
import com.railwaycompany.dto.TrainDto;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.Train;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.TrainService;
import com.railwaycompany.services.exceptions.TrainDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchNumberExistException;
import org.apache.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("trainService")
@Transactional(readOnly = false)
public class TrainServiceImpl implements TrainService {

    private static final Logger LOG = Logger.getLogger(TrainServiceImpl.class.getName());

    @Autowired
    TrainDao trainDao;

    @Autowired
    RouteDao routeDao;

    @Autowired
    RouteService routeService;

    @Autowired
    StationDao stationDao;

    private Train constructTrain(TrainDto trainDto) {
        Route route = routeDao.getRouteByName(trainDto.getRouteName());

        Train train = new Train();
        train.setNumber(trainDto.getNumber());
        train.setRoute(route);
        train.setNumberOfCarriages(trainDto.getNumberOfCarriages());
        return train;
    }

    private TrainDto constructTrainDto(Train train) {
        TrainDto trainDto = new TrainDto();
        trainDto.setId(train.getId());
        trainDto.setNumber(train.getNumber());
        trainDto.setRouteName(train.getRoute().getName());
        trainDto.setNumberOfCarriages(train.getNumberOfCarriages());
        return trainDto;
    }

    @Override
    public void addTrain(TrainDto trainDto) throws TrainWithSuchNumberExistException {
        if (trainDao.getTrainByNumber(trainDto.getNumber()) == null) {
            Train train = constructTrain(trainDto);
            trainDao.create(train);
        } else {
            String message = "Train with number " + trainDto.getNumber() + " already exist: " +
                    "\nid: " + trainDto.getId() +
                    "\nRoute: " + trainDto.getRouteName() +
                    "\nNumber of carriages: " + trainDto.getNumberOfCarriages();
            throw new TrainWithSuchNumberExistException(message);
        }
    }

    @Override
    public void addTrain(int number, long routeId, int numberOfCarriages) throws TrainWithSuchNumberExistException {
        Route route = routeDao.read(routeId);
        Train train = trainDao.getTrainByNumber(number);
        if (train == null) {
            train = new Train();
            train.setNumber(number);
            train.setRoute(route);
            train.setNumberOfCarriages(numberOfCarriages);
            trainDao.create(train);
        } else {
            String message = "Train with number " + number + " already exist: " +
                    "\nid: " + train.getId() +
                    "\nRoute: " + train.getRoute() +
                    "\nNumber of carriages: " + train.getNumberOfCarriages();
            throw new TrainWithSuchNumberExistException(message);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public TrainDto getTrainDtoById(long id) throws TrainDoesNotExistException {
        Train train = trainDao.read(id);
        if (train == null) {
            String message = "Train with id " + id + " does not exist";
            throw new TrainDoesNotExistException(message);
        }
        return constructTrainDto(train);
    }

    @Override
    public TrainDto getTrainDtoByNumber(int number) throws TrainDoesNotExistException {
        Train train = trainDao.getTrainByNumber(number);
        if (train == null) {
            String message = "Train with number " + number + " does not exist";
            throw new TrainDoesNotExistException(message);
        }
        return constructTrainDto(train);
    }

    @Transactional(readOnly = true)
    @Override
    public TrainDto getTrainDtoByRouteId(long routeId) throws TrainDoesNotExistException {
        Train train = trainDao.getTrainByRouteId(routeId);
        if (train == null) {
            String message = "Train with routeId " + routeId + " does not exist";
            throw new TrainDoesNotExistException(message);
        }
        return constructTrainDto(train);
    }

    @Transactional(readOnly = true)
    @Override
    public List<TrainDto> getAllTrains() {
        List<TrainDto> trainDtoList = null;
        List<Train> trainList = trainDao.readAll();
        if (trainList != null && !trainList.isEmpty()) {
            trainDtoList = new ArrayList<>();
            for (Train train : trainList) {
                trainDtoList.add(constructTrainDto(train));
            }
        }
        return trainDtoList;
    }

    @Transactional(readOnly = true)
    @Override
    public int getNumberOfCarriagesByTrainId(long id) throws TrainDoesNotExistException {
        Train train = trainDao.read(id);
        int numberOfCarriages = -1;
        if (train != null) numberOfCarriages = train.getNumberOfCarriages();
        else {
            String message = "Train with id " + id + " does not exist";
            throw new TrainDoesNotExistException(message);
        }
        return numberOfCarriages;
    }

    @Override
    public void updateTrain(TrainDto trainDto) {
        Train train = trainDao.getTrainByNumber(trainDto.getNumber());
        trainDao.update(train);
    }

    @Override
    public void deleteTrain(TrainDto trainDto) {
        Train train = trainDao.getTrainByNumber(trainDto.getNumber());
        trainDao.delete(train);
    }
}