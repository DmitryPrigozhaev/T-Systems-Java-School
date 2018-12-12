package com.railwaycompany.services.api;

import com.railwaycompany.dto.TrainDto;
import com.railwaycompany.services.exceptions.StationDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchNumberExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchRouteExistException;

import java.util.List;

/**
 * Interface of {@link com.railwaycompany.entities.Train} service
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface TrainService {

    void addTrain(TrainDto trainDto) throws TrainWithSuchNumberExistException, TrainWithSuchRouteExistException;

    void addTrain(int number, long routeId, int numberOfCarriages) throws TrainWithSuchNumberExistException;

    TrainDto getTrainDtoById(long id) throws TrainDoesNotExistException;

    TrainDto getTrainDtoByNumber(int number) throws TrainDoesNotExistException;

    TrainDto getTrainDtoByRouteId(long routeId) throws TrainDoesNotExistException;

    TrainDto getTrainDtoByRouteIdAndStations(long routeId, String stationFromName, String stationToName) throws TrainDoesNotExistException, StationDoesNotExistException;

    List<TrainDto> getAllTrains();

    int getNumberOfCarriagesByTrainId(long id) throws TrainDoesNotExistException;

    void updateTrain(TrainDto trainDto) throws TrainWithSuchNumberExistException, TrainWithSuchRouteExistException;

    void deleteTrain(TrainDto trainDto);
}
