package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Train;

public interface TrainDao extends GenericDao<Train> {

    Train getTrainById(long id);

    Train getTrainByNumber(int number);

    Train getTrainByRouteId(long id);

    Train getTrainByRouteName(String routeName);
}
