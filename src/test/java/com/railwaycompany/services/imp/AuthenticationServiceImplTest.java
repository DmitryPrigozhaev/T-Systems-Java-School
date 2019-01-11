package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.UserDao;
import com.railwaycompany.entities.Role;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.api.AuthenticationService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.Collection;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class AuthenticationServiceImplTest {

    private static final Long USER_ID = 1L;
    private static final String USER_EMAIL = "testEmail";
    private static final String NOT_EXIST_USER_EMAIL = "testNotExistEmail";
    private static final String USER_PASSWORD = "testPassword";
    private static final String USER_ROLE = "ROLE_CLIENT";

    @Autowired
    private AuthenticationService authenticationService;

    @Test
    public void testLoadUserByUsername() throws Exception {
        UserDetails userDetails = authenticationService.loadUserByUsername(USER_EMAIL);
        Assert.assertEquals(userDetails.getUsername(), USER_EMAIL);
        Assert.assertEquals(userDetails.getPassword(), USER_PASSWORD);
        Collection<? extends GrantedAuthority> authorities = userDetails.getAuthorities();
        for (GrantedAuthority authority : authorities) {
            Assert.assertEquals(authority.getAuthority(), USER_ROLE);
        }
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByNotExistUsername() throws Exception {
        authenticationService.loadUserByUsername(NOT_EXIST_USER_EMAIL);
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByEmptyUsername() throws Exception {
        authenticationService.loadUserByUsername("");
    }

    @Test(expected = UsernameNotFoundException.class)
    public void testLoadUserByNullUsername() throws Exception {
        authenticationService.loadUserByUsername(null);
    }

    @Configuration
    static class ContextConfiguration {

        @Bean
        public AuthenticationService authenticationService() {
            return new AuthenticationServiceImpl();
        }

        @Bean
        public UserDao userDao() {
            UserDao userDao = mock(UserDao.class);
            User user = new User();
            user.setId(USER_ID);
            user.setEmail(USER_EMAIL);
            user.setPassword(USER_PASSWORD);

            Role role = new Role();
            role.setName(USER_ROLE);

            user.setRole(role);

            when(userDao.getUserByEmail(USER_EMAIL)).thenReturn(user);
            when(userDao.getUserByEmail(NOT_EXIST_USER_EMAIL)).thenReturn(null);

            return userDao;
        }
    }
}