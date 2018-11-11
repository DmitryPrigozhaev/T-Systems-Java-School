package com.railwaycompany.dao.api;

import com.railwaycompany.entities.Role;
import org.springframework.data.jpa.repository.JpaRepository;

public interface RoleDao extends JpaRepository<Role, Long> {
}
