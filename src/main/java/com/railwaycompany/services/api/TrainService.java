package com.railwaycompany.services.api;

import com.railwaycompany.entities.Train;
import com.railwaycompany.services.exceptions.TrainWithSuchNumberExistException;

import java.util.List;

public interface TrainService {

    void addTrain(int number, int seats, String status) throws TrainWithSuchNumberExistException;

    Train getTrain(long id);

    List<Train> getAllTrains();

    void updateTrain(long id);

    void deleteTrain(long id);
}
