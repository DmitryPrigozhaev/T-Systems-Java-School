package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Ticket;

import java.util.List;

/**
 * Interface for Data Access Object of {@link Ticket}
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface TicketDao extends GenericDao<Ticket> {

    List<Ticket> getTicketsByTrainIdAndCarriageNumber(long trainId, int carriageNumber);

    List<Ticket> getAllTicketsByTrainId(long trainId);

    List<Ticket> getTicketByUserIdAndTrainId(long userId, long trainId);

    List<Ticket> getTicketByUserEmail(String email);

    boolean isRegistered(int userId, int trainId);

}
