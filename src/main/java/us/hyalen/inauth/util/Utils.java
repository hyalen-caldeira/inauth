package us.hyalen.inauth.util;

import com.javadocmd.simplelatlng.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import us.hyalen.inauth.core.location.v1.LocationRepository;
import us.hyalen.inauth.domain.Location;

import java.text.DecimalFormat;

public class Utils {
    private static final int NUM_RECORDS = 10000;

    @Autowired
    private LocationRepository repository;

    public void populate() {
        for (int i = 0; i < NUM_RECORDS; i++) {
            LatLng latLng = LatLng.random();

            // Location location = mapsApi.getLocation(latLng.toString());
            DecimalFormat df = new DecimalFormat(".######");

            Location location = new Location();
            location.setLatitude(Double.valueOf(df.format(latLng.getLatitude())));
            location.setLongitude(Double.valueOf(df.format(latLng.getLongitude())));

            repository.save(location);
        }
    }
}
