package us.hyalen.inauth.service;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.context.web.WebAppConfiguration;
import us.hyalen.inauth.Application;
import us.hyalen.inauth.domain.Location;

import java.util.List;
import java.util.Optional;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNull;

@RunWith(SpringRunner.class)
@ContextConfiguration(classes = Application.class)
@WebAppConfiguration
@SpringBootTest
public class LocationServiceTest {
    private static final String ORDER_ID = "1";
    private static final String NON_EXIST_ORDER_ID = "999";
    private static final int TOTAL_ORDERS = 5;

    @TestConfiguration
    static class LocationServiceTestContextConfiguration {
        @Bean
        public LocationService locationService() {
            return new LocationService();
        }
    }

    @Autowired
    private LocationService locationService;

    @Test
    public void testFetchAllLocations() {
        List<Location> result = locationService.getAllLocation();
        assertEquals(TOTAL_ORDERS, result.size());
    }

    @Test
    public void when_AnExistCoordinateIsGiven_then_ValidLocationIsReturned() {
        Optional<Location> result = locationService.getLocationByLatitudeAndLongitude(45D, 55D);
        assertEquals(ORDER_ID, result.get().getLatitude());
    }

//    @Test
//    public void when_ANonExistOrderIdIsGiven_then_NullIsReturned() {
//        Order result = orderService.getOrder(NON_EXIST_ORDER_ID);
//        assertNull(result);
//    }
}