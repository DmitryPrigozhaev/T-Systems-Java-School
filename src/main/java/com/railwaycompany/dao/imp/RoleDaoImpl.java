package com.railwaycompany.dao.imp;

import com.railwaycompany.dao.api.RoleDao;
import com.railwaycompany.entities.Role;
import org.apache.log4j.Logger;
import org.springframework.stereotype.Repository;

import javax.persistence.Query;

@Repository
public class RoleDaoImpl extends AbstractGenericDao<Role> implements RoleDao {

    private static final Logger LOG = Logger.getLogger(RoleDaoImpl.class.getName());

    private static final String GET_ROLE_BY_NAME = "SELECT r FROM Role r WHERE r.name = :name";


    @Override
    public Role getRoleByName(String name) {
        Query query = getCurrentSession().createQuery(GET_ROLE_BY_NAME);
        query.setParameter("name", name);

        Role role = null;
        try {
            role = (Role) query.getSingleResult();
        } catch (Exception e) {
            LOG.warn(e);
        }
        return role;
    }

}