package com.railwaycompany.services.api;

import com.railwaycompany.dto.TrainDto;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.Train;
import com.railwaycompany.services.exceptions.TrainDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchNumberExistException;

import java.util.List;

public interface TrainService {

    void addTrain(int number, long routeId, int numberOfCarriages) throws TrainWithSuchNumberExistException;

    void setRouteForTrainByTrainId(Route route, long id);

    TrainDto getTrainDtoById(long id) throws TrainDoesNotExistException;

    TrainDto getTrainDtoByRouteId(long id) throws TrainDoesNotExistException;

    List<TrainDto> getAllTrainsDto();

    int getNumberOfCarriagesByTrainId(long id) throws TrainDoesNotExistException;

    void updateTrain(Train train);

    void deleteTrain(Train train);
}
