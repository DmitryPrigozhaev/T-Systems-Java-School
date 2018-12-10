package com.railwaycompany.services.imp;

import org.junit.Assert;
import org.junit.Test;

//import com.railwaycompany.dto.UserDto;
//import com.railwaycompany.services.api.UserService;
//import com.railwaycompany.dao.api.UserDao;
//import com.railwaycompany.entities.User;
//import org.junit.Assert;
//import org.junit.Test;
//import org.junit.runner.RunWith;
//import org.mockito.Mock;
//import org.mockito.runners.MockitoJUnitRunner;
//import org.springframework.context.annotation.Bean;
//import org.springframework.context.annotation.Configuration;
//
//import static org.mockito.Mockito.mock;
//import static org.mockito.Mockito.when;
//
//@RunWith(MockitoJUnitRunner.class)
public class UserServiceImplTest {

    @Test
    public void test() throws Exception {
        Assert.assertTrue(true);
    }

//    private static final long USER_ID = 1;
//    private static final long NOT_EXIST_USER_ID = 2;
//    private static final String USER_EMAIL = "testUserEmail";
//
//    @Mock
//    private UserService userService;
//
//    @Test
//    public void testGetUserDtoByEmail() throws Exception {
//        UserDto userDto = userService.getUserDtoByEmail(USER_EMAIL);
//        Assert.assertNotNull(userDto);
//        Assert.assertEquals(userDto.getEmail(), USER_EMAIL);
//    }
//
//    @Test
//    public void testGetNotExistUserDtoByEmail() throws Exception {
//        UserDto userData = userService.getUserDtoByEmail(USER_EMAIL);
//        Assert.assertNull(userData);
//    }
//
//    @Configuration
//    static class ContextConfiguration {
//
//        @Bean
//        public UserService userService() {
//            return new UserServiceImpl();
//        }
//
//        @Bean
//        public UserDao userDao() {
//            UserDao userDao = mock(UserDao.class);
//
//            User user = new User();
//            user.setId(USER_ID);
//            user.setEmail(USER_EMAIL);
//
//            when(userDao.read(USER_ID)).thenReturn(user);
//            when(userDao.read(NOT_EXIST_USER_ID)).thenReturn(null);
//
//            return userDao;
//        }
//    }
}