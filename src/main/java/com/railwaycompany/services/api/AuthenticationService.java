package com.railwaycompany.services.api;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * Interface for AuthenticationService
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface AuthenticationService extends UserDetailsService {

    @Override
    UserDetails loadUserByUsername(String username);

}