package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Role;

public interface RoleDao extends GenericDao<Role> {

    Role getRoleByName(String name);

}
