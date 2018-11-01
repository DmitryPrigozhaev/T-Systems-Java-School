package com.railwaycompany.services.api;

import com.railwaycompany.dto.UserDto;
import com.railwaycompany.entities.User;
import com.railwaycompany.exceptions.AlreadyRegisteredException;

import java.util.List;

public interface UserService {

    void addUser(User user) throws AlreadyRegisteredException;

    UserDto getUserDto(long id);

    UserDto getUserDtoByEmail(String email);

    List<UserDto> getAllDtoUsers();

    void updateUser(User user);

    void deleteUser(User user);

    boolean isUserEmailUnique(String email);
}