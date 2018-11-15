package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RoleDao;
import com.railwaycompany.dao.api.UserDao;
import com.railwaycompany.dto.UserDto;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.api.UserService;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;
import com.railwaycompany.utils.DtoConverter;
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

    @Override
    public void saveUserDto(UserDto userDto) throws AlreadyRegisteredException {
        if (isUserEmailUnique(userDto.getEmail())) {

            userDto.setPassword(passwordEncoder.encode(userDto.getPassword()));
            User user = DtoConverter.constructUser(userDto);
            user.setRole(roleDao.getRoleByName("ROLE_USER"));
            userDao.create(user);

        } else {
            String message = "User with email \"" + userDto.getEmail() + "\" is already exist!";
            throw new AlreadyRegisteredException(message);
        }
    }

    @Transactional(readOnly = true)
    @Override
    public UserDto getUserDto(long userId) {
        User user = userDao.read(userId);
        UserDto userDto = null;
        if (user != null) {
            userDto = DtoConverter.constructUserDto(user);
        }
        return userDto;
    }

    @Override
    public UserDto getUserDtoByEmail(String email) {
        User user = userDao.getUserByEmail(email);
        UserDto userDto = null;
        if (user != null) {
            userDto = DtoConverter.constructUserDto(user);
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
                userDtoList.add(DtoConverter.constructUserDto(user));
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