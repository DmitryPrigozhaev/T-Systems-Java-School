package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Train;
import com.railwaycompany.exceptions.TrainWithSuchNumberExistException;

public interface TrainDao extends GenericDao<Train> {

    void addTrain(int number, int seats, String status) throws TrainWithSuchNumberExistException;

    int getNumber(long id);

    void setNumber(long id, int number);

    String getTrainStatus(long id);

    void setTrainStatus(long id, String status);

    int getNumberOfSeats(long id);

    void setNumberOfSeats(long id, int number);

}
