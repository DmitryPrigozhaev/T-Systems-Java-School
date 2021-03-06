package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.TicketDao;
import com.railwaycompany.entities.Ticket;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

@Repository
public class TicketDaoImpl extends AbstractGenericDao<Ticket> implements TicketDao {

    private static final Logger LOG = Logger.getLogger(TicketDaoImpl.class.getName());

    private static final String GET_TICKETS_BY_TRAIN_ID = "SELECT t FROM Ticket t WHERE t.train.id = :trainId";

    private static final String GET_TICKETS_BY_USER_EMAIL = "SELECT t FROM Ticket t WHERE t.user.email = :email";

    private static final String GET_TICKETS_BY_CARRIAGE_AND_TRAIN_ID = "SELECT t FROM Ticket t WHERE t.carriage = :carriage " +
            "AND" + " t.train.id = :trainId";

    private static final String GET_TICKETS_BY_TRAIN_ID_AND_USER_ID = "SELECT t FROM Ticket t WHERE t.train.id = :trainId " +
            "AND" + " t.user.id = :userId";

    @Override
    public List<Ticket> getAllTicketsByTrainId(long trainId) {
        Query query = getCurrentSession().createQuery(GET_TICKETS_BY_TRAIN_ID);
        query.setParameter("trainId", trainId);

        List resultList = query.getResultList();
        return getTickets(resultList);
    }

    @Override
    public List<Ticket> getTicketsByTrainIdAndCarriageNumber(long trainId, int carriageNumber) {
        Query query = getCurrentSession().createQuery(GET_TICKETS_BY_CARRIAGE_AND_TRAIN_ID);
        query.setParameter("carriage", carriageNumber);
        query.setParameter("trainId", trainId);

        List queryResultList = query.getResultList();
        return getTickets(queryResultList);
    }

    @Override
    public List<Ticket> getTicketByUserIdAndTrainId(long userId, long trainId) {
        Query query = getCurrentSession().createQuery(GET_TICKETS_BY_TRAIN_ID_AND_USER_ID);
        query.setParameter("userId", userId);
        query.setParameter("trainId", trainId);

        List resultList = query.getResultList();
        return getTickets(resultList);
    }

    @Override
    public List<Ticket> getTicketByUserEmail(String email) {
        Query query = getCurrentSession().createQuery(GET_TICKETS_BY_USER_EMAIL);
        query.setParameter("email", email);

        List resultList = query.getResultList();
        return getTickets(resultList);
    }

    @Override
    public boolean isRegistered(int userId, int trainId) {
        List<Ticket> ticketList = getTicketByUserIdAndTrainId(userId, trainId);
        boolean registered = false;

        if (ticketList != null) {
            registered = true;
        } else {
            LOG.warn("No tickets was found for userId = " + userId + " and trainId = " + trainId);
        }
        return registered;
    }

    private List<Ticket> getTickets(List resultList) {
        List<Ticket> ticketList = null;
        if (resultList != null && !resultList.isEmpty()) {
            ticketList = new ArrayList<>();
            for (Object o : resultList) {
                if (o instanceof Ticket) {
                    ticketList.add((Ticket) o);
                }
            }
            Collections.sort(ticketList);
        }
        return ticketList;
    }
}