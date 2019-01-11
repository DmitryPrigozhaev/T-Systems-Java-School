package com.railwaycompany.services.imp;

import com.railwaycompany.dao.api.RouteDao;
import com.railwaycompany.dao.api.RoutePointDao;
import com.railwaycompany.dao.api.StationDao;
import com.railwaycompany.dao.api.TrainDao;
import com.railwaycompany.dto.TrainDto;
import com.railwaycompany.entities.Route;
import com.railwaycompany.entities.Train;
import com.railwaycompany.services.api.RouteService;
import com.railwaycompany.services.api.TrainService;
import com.railwaycompany.services.exceptions.TrainDoesNotExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchNumberExistException;
import com.railwaycompany.services.exceptions.TrainWithSuchRouteExistException;
import org.junit.Assert;
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
public class TrainServiceImplTest {

    private static final Long TRAIN_ID = 1L;
    private static final Integer EXIST_TRAIN_NUMBER = 50;
    private static final Integer NOT_EXIST_TRAIN_NUMBER = 60;
    private static final Integer NUMBER_OF_CARRIAGES = 15;

    private static final String EXIST_ROUTE_NAME = "testExistRouteName";
    private static final String NOT_EXIST_ROUTE_NAME = "testNotExistRouteName";


    @Autowired
    private TrainService trainService;

    @Test
    public void testAddTrain() throws Exception {
        TrainDto trainDto = new TrainDto();
        trainDto.setNumber(NOT_EXIST_TRAIN_NUMBER);
        trainDto.setRouteName(NOT_EXIST_ROUTE_NAME);
        trainService.addTrain(trainDto);
    }

    @Test(expected = TrainWithSuchNumberExistException.class)
    public void testAddExistNumberTrain() throws Exception {
        TrainDto trainDto = new TrainDto();
        trainDto.setNumber(EXIST_TRAIN_NUMBER);
        trainService.addTrain(trainDto);
    }

    @Test(expected = TrainWithSuchRouteExistException.class)
    public void testAddExistRouteTrain() throws Exception {
        TrainDto trainDto = new TrainDto();
        trainDto.setNumber(NOT_EXIST_TRAIN_NUMBER);
        trainDto.setRouteName(EXIST_ROUTE_NAME);
        trainService.addTrain(trainDto);
    }

    @Test
    public void testGetExistTrain() throws Exception {
        TrainDto trainDto = trainService.getTrainDtoByNumber(EXIST_TRAIN_NUMBER);
        Assert.assertNotNull(trainDto);
        Assert.assertEquals(TRAIN_ID, trainDto.getId());
    }

    @Test(expected = TrainDoesNotExistException.class)
    public void testGetNotExistTrain() throws Exception {
        TrainDto trainDto = trainService.getTrainDtoByNumber(NOT_EXIST_TRAIN_NUMBER);
        Assert.assertNull(trainDto);
    }

    @Test
    public void testGetAllTrains() {
        List<TrainDto> trainDtoList = trainService.getAllTrains();
        Assert.assertNotNull(trainDtoList);
        Assert.assertFalse(trainDtoList.isEmpty());
    }

    @Configuration
    static class ContextConfiguration {

        @Bean
        public TrainService trainService() {
            return new TrainServiceImpl();
        }

        @Bean
        public TrainDao trainDao() {
            TrainDao trainDao = mock(TrainDao.class);

            Route route = new Route();
            route.setName(EXIST_ROUTE_NAME);

            Train train = new Train();
            train.setId(TRAIN_ID);
            train.setNumber(EXIST_TRAIN_NUMBER);
            train.setRoute(route);
            train.setNumberOfCarriages(NUMBER_OF_CARRIAGES);

            List<Train> trainList = new ArrayList<>();
            trainList.add(train);

            when(trainDao.getTrainByNumber(EXIST_TRAIN_NUMBER)).thenReturn(train);
            when(trainDao.getTrainByRouteName(EXIST_ROUTE_NAME)).thenReturn(train);
            when(trainDao.readAll()).thenReturn(trainList);

            return trainDao;
        }

        @Bean
        public StationDao stationDao() {
            return mock(StationDao.class);
        }

        @Bean
        public RouteDao routeDao() {
            return mock(RouteDao.class);
        }

        @Bean
        public RoutePointDao routePointDao() {
            return mock(RoutePointDao.class);
        }

        @Bean
        public RouteService routeService() {
            return mock(RouteService.class);
        }
    }
}