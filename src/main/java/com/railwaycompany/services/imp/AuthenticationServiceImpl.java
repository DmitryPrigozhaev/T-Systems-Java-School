package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.UserDao;
import com.railwaycompany.entities.Role;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.api.AuthenticationService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.HashSet;
import java.util.Set;

@Service
public class AuthenticationServiceImpl implements AuthenticationService {

    @Autowired
    private UserDao userDao;

    @Transactional(readOnly = true)
    @Override
    public UserDetails loadUserByUsername(final String username) throws UsernameNotFoundException {
        if (username != null) {
            User user = userDao.getUserByEmail(username);
            if (user != null) {
                Set<GrantedAuthority> grantedAuthorities = new HashSet<>();

//                 вместо этого:
                for (Role role  : user.getRoles()) {
                    grantedAuthorities.add(new SimpleGrantedAuthority(role.getName()));

                }
//                было это:
//                grantedAuthorities.add(new SimpleGrantedAuthority(user.getRole().toString()));

                return new org.springframework.security.core.userdetails.User(user.getEmail(), user.getPassword(), grantedAuthorities);

            } else {
                throw new UsernameNotFoundException("User with email " + username + " not found");
            }

        } else {
            throw new UsernameNotFoundException("Empty or not valid username");
        }
    }
}