package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.TrainDao;
import com.railwaycompany.entities.Train;
import com.railwaycompany.exceptions.TrainWithSuchNumberExistException;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class TrainDaoImpl extends AbstractGenericDao<Train> implements TrainDao {

    private static final Logger LOG = Logger.getLogger(TrainDaoImpl.class.getName());

    private static final String GET_TRAIN_BY_NUMBER = "SELECT t FROM Train t WHERE t.number = :number";

    @Override
    public void addTrain(int number, int seats, String status) throws TrainWithSuchNumberExistException {

        Query query = getCurrentSession().createQuery(GET_TRAIN_BY_NUMBER);
        query.setParameter("number", number);

        if (query.getSingleResult() == null) {
            Train train = new Train();
            train.setNumber(number);
            train.setSeats(seats);
            train.setStatus(status);
            create(train);
        } else {
            String message = "Train with number " + number + " already exist!";
            throw new TrainWithSuchNumberExistException(message);
        }
    }

    @Override
    public int getNumber(long id) {
        Train train = read(id);
        return train.getNumber();
    }

    @Override
    public void setNumber(long id, int number) {
        Train train = read(id);
        train.setNumber(number);
    }

    @Override
    public String getTrainStatus(long id) {
        Train train = read(id);
        return train.getStatus();
    }

    @Override
    public void setTrainStatus(long id, String status) {
        Train train = read(id);
        train.setStatus(status);
    }

    @Override
    public int getNumberOfSeats(long id) {
        Train train = read(id);
        return train.getSeats();
    }

    @Override
    public void setNumberOfSeats(long id, int number) {
        Train train = read(id);
        train.setSeats(number);
    }
}
