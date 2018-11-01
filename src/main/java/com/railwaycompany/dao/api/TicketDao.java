package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Ticket;

import java.util.List;

public interface TicketDao extends GenericDao<Ticket> {

    int[] getPurchasedSeatsByCarriageAndTrainId(int trainId, int carriage);

    boolean hasBeenRegistered(int trainId, int userId);

    List<Ticket> getAllTicketsByTrainId(int trainId);
}
