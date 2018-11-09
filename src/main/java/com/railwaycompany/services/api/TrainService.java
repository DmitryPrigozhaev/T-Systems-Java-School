package com.railwaycompany.services.api;

import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.Train;
import com.railwaycompany.services.exceptions.TrainDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchNumberExistException;

import java.util.List;

public interface TrainService {

    void addTrain(Train train) throws TrainWithSuchNumberExistException;

    void addTrain(int number, long routeId, int numberOfCarriages) throws TrainWithSuchNumberExistException;

    void setRouteForTrainByTrainId(Route route, long id);

    Train getTrainById(long id) throws TrainDoesNotExistException;

    Train getTrainByNumber(int number) throws TrainDoesNotExistException;

    Train getTrainByRouteId(long id) throws TrainDoesNotExistException;

    List<Train> getAllTrains();

    int getNumberOfCarriagesByTrainId(long id) throws TrainDoesNotExistException;

    void updateTrain(Train train);

    void deleteTrain(Train train);
}
