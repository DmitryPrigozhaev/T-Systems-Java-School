package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.UserDao;
import com.railwaycompany.dto.UserDto;
import com.railwaycompany.entities.User;
import com.railwaycompany.exceptions.AlreadyRegisteredException;
import com.railwaycompany.services.api.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service("userService")
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    private UserDto constructUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthDate(userDto.getBirthDate());
        userDto.setRole(user.getRole());
        return userDto;
    }

    @Override
    public void addUser(User user) throws AlreadyRegisteredException {
        if (isUserEmailUnique(user.getEmail())) {
            userDao.create(user);
        } else {
            String message = "User with email \"" + user.getEmail() + "\" is already exist!";
            throw new AlreadyRegisteredException(message);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserDto(long userId) {
        UserDto userDto = null;
        User user =  userDao.read(userId);
        if (user != null) userDto = constructUserDto(user);
        return userDto;
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
        UserDto userDto = null;
        User user =  userDao.getUserByEmail(email);
        if (user != null) userDto = constructUserDto(user);
        return userDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAllDtoUsers() {
        List<UserDto> userDtoList = null;
        List<User> listUser = userDao.readAll();
        if (listUser != null && !listUser.isEmpty()) {
            userDtoList = new ArrayList<>();
            for (User user : listUser) {
                userDtoList.add(constructUserDto(user));
            }
        }
        return userDtoList;
    }

    @Override
    public void updateUser(User user) {
        userDao.update(user);
    }

    @Override
    public void deleteUser(User entity) {
        userDao.delete(entity);
    }

    @Override
    public boolean isUserEmailUnique(String email) {
        User user = userDao.getUserByEmail(email);
        return (user == null);
    }
}