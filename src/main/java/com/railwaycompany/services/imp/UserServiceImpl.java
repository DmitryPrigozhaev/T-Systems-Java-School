package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RoleDao;
import com.railwaycompany.dao.api.UserDao;
import com.railwaycompany.dto.UserDto;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.api.UserService;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import com.railwaycompany.utils.DateConverter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.ArrayList;
import java.util.List;

@Service
@Transactional(readOnly = false)
public class UserServiceImpl implements UserService {

    @Autowired
    private UserDao userDao;

    @Autowired
    private RoleDao roleDao;

    @Autowired
    PasswordEncoder passwordEncoder;

    private User constructUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthDate(DateConverter.convertDate(userDto.getBirthDate()));
        user.setRole(roleDao.getRoleByName("ROLE_CLIENT"));
        return user;
    }

    private UserDto constructUserDto(User user) {
        UserDto userDto = new UserDto();
        userDto.setEmail(user.getEmail());
        userDto.setPassword(user.getPassword());
        userDto.setConfirmPassword(user.getPassword());
        userDto.setFirstName(user.getFirstName());
        userDto.setLastName(user.getLastName());
        userDto.setBirthDate(user.getBirthDate().toString());
        userDto.setRole(user.getRole().getName());
        return userDto;
    }

    @Override
    public void saveUserDto(UserDto userDto) throws AlreadyRegisteredException {
        if (isUserEmailUnique(userDto.getEmail())) {

            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = constructUser(userDto);
            userDao.create(user);

        } else {
            String message = "User with email \"" + userDto.getEmail() + "\" is already exist!";
            throw new AlreadyRegisteredException(message);
        }
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
        User user = userDao.getUserByEmail(email);
        UserDto userDto = null;
        if (user != null) {
            userDto = constructUserDto(user);
        }
        return userDto;
    }

    @Transactional(readOnly = true)
    @Override
    public List<UserDto> getAllUsersDto() {
        List<User> userList = userDao.readAll();
        List<UserDto> userDtoList = null;
        if (userList != null && !userList.isEmpty()) {
            userDtoList = new ArrayList<>();
            for (User user : userList) {
                userDtoList.add(constructUserDto(user));
            }
        }
        return userDtoList;
    }

    @Override
    public void updateUserDto(UserDto userDto) {
        User user = userDao.getUserByEmail(userDto.getEmail());
        userDao.update(user);
    }

    @Override
    public void deleteUserDto(UserDto userDto) {
        User user = userDao.getUserByEmail(userDto.getEmail());
        userDao.delete(user);
    }

    @Override
    public boolean isUserEmailUnique(String email) {
        User user = userDao.getUserByEmail(email);
        return (user == null);
    }
}