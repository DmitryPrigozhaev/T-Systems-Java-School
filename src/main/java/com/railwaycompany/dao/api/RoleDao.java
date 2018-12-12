package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Role;

/**
 * Interface for Data Access Object of {@link Role}
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface RoleDao extends GenericDao<Role> {

    Role getRoleByName(String name);

}
