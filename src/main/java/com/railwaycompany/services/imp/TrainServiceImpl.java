package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RouteDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.dao.api.TrainDao;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.Train;
import com.railwaycompany.services.api.TrainService;
import com.railwaycompany.services.exceptions.TrainDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchNumberExistException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service("trainService")
@Transactional(readOnly = false)
public class TrainServiceImpl implements TrainService {

    @Autowired
    TrainDao trainDao;

    @Autowired
    RouteDao routeDao;

    @Autowired
    StationDao stationDao;

    @Override
    public void addTrain(Train train) throws TrainWithSuchNumberExistException {
        if (trainDao.getTrainByNumber(train.getNumber()) == null) {
            trainDao.create(train);
        } else {
            String message = "Train with number " + train.getNumber() + " already exist: " +
                    "\nid: " + train.getId() +
                    "\nRoute: " + train.getRoute() +
                    "\nNumber of carriages: " + train.getNumberOfCarriages();
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

    @Override
    public void setRouteForTrainByTrainId(Route route, long id) {
        Train train = trainDao.read(id);
        train.setRoute(route);
    }

    @Transactional(readOnly = true)
    @Override
    public Train getTrainById(long id) throws TrainDoesNotExistException {
        Train train = trainDao.read(id);
        if (train == null) {
            String message = "Train with id " + id + " does not exist";
            throw new TrainDoesNotExistException(message);
        }
        return train;
    }

    @Override
    public Train getTrainByNumber(int number) throws TrainDoesNotExistException {
        Train train = trainDao.getTrainByNumber(number);
        if (train == null) {
            String message = "Train with number " + number + " does not exist";
            throw new TrainDoesNotExistException(message);
        }
        return train;
    }

    @Transactional(readOnly = true)
    @Override
    public Train getTrainByRouteId(long routeId) throws TrainDoesNotExistException {
        Train train = trainDao.getTrainByRouteId(routeId);
        if (train == null) {
            String message = "Train with routeId " + routeId + " does not exist";
            throw new TrainDoesNotExistException(message);
        }
        return train;
    }

    @Transactional(readOnly = true)
    @Override
    public List<Train> getAllTrains() {
        return trainDao.readAll();
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
    public void updateTrain(Train train) {
        trainDao.update(train);
    }

    @Override
    public void deleteTrain(Train train) {
        trainDao.delete(train);
    }
}