package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Train;

/**
 * Interface for Data Access Object of {@link Train}
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface TrainDao extends GenericDao<Train> {

    Train getTrainById(long id);

    Train getTrainByNumber(int number);

    Train getTrainByRouteId(long id);

    Train getTrainByRouteName(String routeName);
}
