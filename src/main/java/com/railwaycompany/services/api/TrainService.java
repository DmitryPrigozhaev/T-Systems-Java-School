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

    TrainDto getTrainDtoByNumber(int number) throws TrainDoesNotExistException;

    TrainDto getTrainDtoByRouteIdAndStations(long routeId, String stationFromName, String stationToName) throws TrainDoesNotExistException, StationDoesNotExistException;

    List<TrainDto> getAllTrains();

    void updateTrain(TrainDto trainDto) throws TrainWithSuchNumberExistException, TrainWithSuchRouteExistException;

    void deleteTrain(TrainDto trainDto);
}
