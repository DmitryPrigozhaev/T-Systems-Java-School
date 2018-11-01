package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.TrainDao;
import com.railwaycompany.entities.Train;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class TrainDaoImpl extends AbstractGenericDao<Train> implements TrainDao {

    private static final Logger LOG = Logger.getLogger(TrainDaoImpl.class.getName());

    private static final String GET_TRAIN_BY_NUMBER = "SELECT t FROM Train t WHERE t.number = :number";

    @Override
    public Train getTrainByNumber(int number) {
        Query query = getCurrentSession().createQuery(GET_TRAIN_BY_NUMBER);
        query.setParameter("number", number);

        Train train = null;
        try {
            train = (Train) query.getSingleResult();
        } catch (Exception e){
            LOG.warn(e);
        }
        return train;
    }

}