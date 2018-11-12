package com.railwaycompany.services.api;

import com.railwaycompany.dto.UserDto;
import com.railwaycompany.entities.User;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;

import java.util.List;

public interface UserService {

    void saveUser(UserDto userDto) throws AlreadyRegisteredException;

    User getUser(long id);

    User getUserByEmail(String email);

    List<User> getAllUsers();

    void updateUser(User user);

    void deleteUser(User user);

    boolean isUserEmailUnique(String email);
}