package com.railwaycompany.services.api;

import com.railwaycompany.dto.UserDto;
import com.railwaycompany.services.exceptions.AlreadyRegisteredException;

import java.util.List;

/**
 * Interface of {@link com.railwaycompany.entities.User} service
 *
 * @author Dmitry Prigozhaev
 * @version 1.0
 */

public interface UserService {

    void saveUserDto(UserDto userDto) throws AlreadyRegisteredException;

    UserDto getUserDtoByEmail(String email);

    List<UserDto> getAllUsersDto();

    void updateUserDto(UserDto userDto);

    void deleteUserDto(UserDto userDto);

    boolean isUserEmailUnique(String email);
}