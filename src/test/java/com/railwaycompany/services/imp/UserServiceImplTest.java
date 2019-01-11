package com.railwaycompany.services.imp;

import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import org.junit.Assert;
import org.junit.Test;

import com.railwaycompany.dto.UserDto;
import com.railwaycompany.services.api.UserService;
import com.railwaycompany.dao.api.UserDao;
import com.railwaycompany.entities.User;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    private static final long USER_ID = 1;
    private static final long NOT_EXIST_USER_ID = 2;
    private static final String NOT_EXIST_USER_EMAIL = "testUserEmail";
    private static final String EXIST_USER_EMAIL = "testExistUserEmail";

    @Mock
    private UserService userService;

    @Test
    public void testSaveUserDto() throws AlreadyRegisteredException {
        UserDto userDto = new UserDto();
        userDto.setEmail(NOT_EXIST_USER_EMAIL);
        userService.saveUserDto(userDto);
    }

    @Test
    public void testGetNotExistUserDtoByEmail() {
        UserDto userData = userService.getUserDtoByEmail(NOT_EXIST_USER_EMAIL);
        Assert.assertNull(userData);
    }

    @Configuration
    static class ContextConfiguration {

        @Bean
        public UserService userService() {
            return new UserServiceImpl();
        }

        @Bean
        public UserDao userDao() {
            UserDao userDao = mock(UserDao.class);

            User user = new User();
            user.setId(USER_ID);
            user.setEmail(EXIST_USER_EMAIL);

            when(userDao.getUserByEmail(EXIST_USER_EMAIL)).thenReturn(user);
            when(userDao.getUserByEmail(NOT_EXIST_USER_EMAIL)).thenReturn(null);

            when(userDao.read(USER_ID)).thenReturn(user);
            when(userDao.read(NOT_EXIST_USER_ID)).thenReturn(null);

            return userDao;
        }
    }
}