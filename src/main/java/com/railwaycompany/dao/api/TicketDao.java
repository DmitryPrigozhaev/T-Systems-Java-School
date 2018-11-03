package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Ticket;

import java.util.List;

public interface TicketDao extends GenericDao<Ticket> {

    int[] getPurchasedSeatsByCarriageAndTrainId(long trainId, int carriageNumber);

    List<Ticket> getAllTicketsByTrainId(long trainId);

    List<Ticket> getTicketByUserIdAndTrainId(long userId, long trainId);

    boolean isRegistered(int userId, int trainId);

}
