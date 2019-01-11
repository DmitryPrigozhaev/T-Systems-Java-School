package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RoutePointDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.dto.StationDto;
import com.railwaycompany.entities.RoutePoint;
import com.railwaycompany.entities.Station;
import com.railwaycompany.services.api.StationService;
import com.railwaycompany.services.exceptions.StationWithSuchNameExistException;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.test.context.support.AnnotationConfigContextLoader;

import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.mock;
import static org.mockito.Mockito.when;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(loader = AnnotationConfigContextLoader.class)
public class StationServiceImplTest {

    private static final Long STATION_ID = 1L;
    private static final String EXIST_STATION_NAME = "testStationName";
    private static final String NOT_EXIST_STATION_NAME = "testNotExistStationName";

    @Autowired
    private StationService stationService;

    @Test
    public void testGetStationByName() throws Exception {
        StationDto stationDto = stationService.getStationByName(EXIST_STATION_NAME);
        Assert.assertNotNull(stationDto);
        Assert.assertEquals(stationDto.getId(), STATION_ID);
        Assert.assertEquals(stationDto.getName(), EXIST_STATION_NAME);
    }

    @Test(expected = StationWithSuchNameExistException.class)
    public void testAddExistStation() throws Exception {
        StationDto stationDto = new StationDto();
        stationDto.setId(STATION_ID);
        stationDto.setName(EXIST_STATION_NAME);
        stationDto.setRoutePointDtoList(new ArrayList<>());

        stationService.addStation(stationDto);
    }

    @Test
    public void testAddStation() throws Exception {
        StationDto stationDto = new StationDto();
        stationDto.setId(STATION_ID);
        stationDto.setName(NOT_EXIST_STATION_NAME);
        stationDto.setRoutePointDtoList(new ArrayList<>());

        stationService.addStation(stationDto);
    }

    @Test
    public void testGetAll() throws Exception {
        List<StationDto> stationDtoList = stationService.getAllStation();
        Assert.assertNotNull(stationDtoList);
        Assert.assertFalse(stationDtoList.isEmpty());
        StationDto stationDto = stationDtoList.get(0);
        Assert.assertEquals(stationDto.getId(), STATION_ID);
        Assert.assertEquals(stationDto.getName(), EXIST_STATION_NAME);
    }

    @Configuration
    static class ContextConfiguration {

        @Bean
        public StationService stationService() {
            return new StationServiceImpl();
        }

        @Bean
        public StationDao stationDao() {
            Station station = new Station();
            station.setId(STATION_ID);
            station.setName(EXIST_STATION_NAME);

            List<Station> stationList = new ArrayList<>();
            stationList.add(station);

            StationDao stationDao = mock(StationDao.class);

            when(stationDao.getStationByName(EXIST_STATION_NAME)).thenReturn(station);
            when(stationDao.getStationByName(NOT_EXIST_STATION_NAME)).thenReturn(null);
            when(stationDao.readAll()).thenReturn(stationList);

            return stationDao;
        }

        @Bean
        public RoutePointDao routePointDao() {
            return mock(RoutePointDao.class);
        }

    }
}