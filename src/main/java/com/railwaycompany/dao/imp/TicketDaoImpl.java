package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.TicketDao;
import com.railwaycompany.entities.Ticket;
import org.apache.log4j.Logger;

import javax.persistence.NoResultException;
import javax.persistence.Query;
import java.util.ArrayList;
import java.util.List;

public class TicketDaoImpl extends AbstractGenericDao<Ticket> implements TicketDao {

    private static final Logger LOG = Logger.getLogger(TicketDaoImpl.class.getName());

    private static final String TICKET_COUNT_BY_TRAIN_ID = "SELECT t FROM Ticket t WHERE t.train.id = :trainId";

    private static final String TICKET_BY_TRAIN_AND_USER_ID = "SELECT t FROM Ticket t WHERE t.train.id = :trainId " +
            "AND" + " t.user.id = :userId";

    private static final String TICKET_BY_TRAIN_ID = "SELECT t FROM Ticket t WHERE t.train.id = :trainId";

    @Override
    public int countOfTicketsByTrainId(int trainId) {

        Query query = getCurrentSession().createQuery(TICKET_COUNT_BY_TRAIN_ID);
        query.setParameter("trainId", trainId);

        int count = 0;
        try {
            count = query.getResultList().size();
        } catch (NoResultException e) {
            LOG.warn("No tickets was found for trainId: " + trainId);
        }
        return count;
    }

    @Override
    public boolean hasBeenRegistered(int trainId, int userId) {

        Query query = getCurrentSession().createQuery(TICKET_BY_TRAIN_AND_USER_ID);
        query.setParameter("trainId", trainId);
        query.setParameter("userId", userId);
        boolean registered = false;
        try {
            Ticket ticket = (Ticket) query.getSingleResult();
            registered = (ticket != null);
        } catch (NoResultException e) {
            LOG.warn("No tickets was found for trainId: " + trainId);
        }
        return registered;
    }

    @Override
    public List<Ticket> getAllTicketsByTrainId(int trainId) {

        Query query = getCurrentSession().createQuery(TICKET_BY_TRAIN_ID);
        query.setParameter("trainId", trainId);

        List<Ticket> ticketList = null;

        List resultList = query.getResultList();
        if (resultList != null && !resultList.isEmpty()) {
            ticketList = new ArrayList<>();
            for (Object o : resultList) {
                if (o instanceof Ticket) {
                    ticketList.add((Ticket) o);
                }
            }
        }
        return ticketList;
    }

}
