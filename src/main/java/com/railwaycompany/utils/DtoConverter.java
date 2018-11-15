package com.railwaycompany.utils;

import com.railwaycompany.dto.RouteDto;
import com.railwaycompany.dto.UserDto;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.User;
import org.springframework.stereotype.Component;

@Component
public class DtoConverter {

    public static User constructUser(UserDto userDto) {
        User user = new User();
        user.setEmail(userDto.getEmail());
        user.setPassword(userDto.getPassword());
        user.setFirstName(userDto.getFirstName());
        user.setLastName(userDto.getLastName());
        user.setBirthDate(DateConverter.convertDate(userDto.getBirthDate()));
        return user;
    }

    public static UserDto constructUserDto(User user) {
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

    public static RouteDto constructRouteDto(RoutePoint routePoint) {
        RouteDto routeDto = new RouteDto();
        routeDto.setRouteName(routePoint.getRoute().getName());
        routeDto.setStation(routePoint.getStation().getName());
        routeDto.setDateArrival(routePoint.getDateArrival().toString());
        routeDto.setDateDeparture(routePoint.getDateDeparture().toString());
        return routeDto;
    }

}
