package com.railwaycompany.services.api;

import com.railwaycompany.dto.TrainDto;
import com.railwaycompany.services.exceptions.TrainDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchNumberExistException;

import java.util.List;

public interface TrainService {

    void addTrain(TrainDto trainDto) throws TrainWithSuchNumberExistException;

    void addTrain(int number, long routeId, int numberOfCarriages) throws TrainWithSuchNumberExistException;

    TrainDto getTrainDtoById(long id) throws TrainDoesNotExistException;

    TrainDto getTrainDtoByNumber(int number) throws TrainDoesNotExistException;

    TrainDto getTrainDtoByRouteId(long id) throws TrainDoesNotExistException;

    List<TrainDto> getAllTrains();

    int getNumberOfCarriagesByTrainId(long id) throws TrainDoesNotExistException;

    void updateTrain(TrainDto trainDto);

    void deleteTrain(TrainDto trainDto);
}
