package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Station;

/**
 * Interface for Data Access Object of {@link Station}
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface StationDao extends GenericDao<Station> {

    Station getStationByName(String name);

}
