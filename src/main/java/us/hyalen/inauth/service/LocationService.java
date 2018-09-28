package us.hyalen.inauth.service;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.hyalen.inauth.connection.GoogleMapsApi;
import us.hyalen.inauth.domain.Location;
import us.hyalen.inauth.repository.LocationRepository;

import java.text.DecimalFormat;
import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    private static final int NUM_RECORDS = 10000;

    @Autowired
    private LocationRepository repository;

    public List<Location> getAllLocation() {
        return repository.findAll();
    }

    public Optional<Location> getLocationByLatitudeAndLongitude(Double latitude, Double longitude) {
        return repository.findByLatitudeAndLongitude(latitude, longitude);
    }

    public void populate() {
        int count = NUM_RECORDS;

        for (int i = 0; i < NUM_RECORDS; i++) {
            LatLng latLng = LatLng.random();

            // Location location = mapsApi.getLocation(latLng.toString());
            DecimalFormat df = new DecimalFormat(".######");

            Location location = new Location();
            location.setLatitude(Double.valueOf(df.format(latLng.getLatitude())));
            location.setLongitude(Double.valueOf(df.format(latLng.getLongitude())));

            repository.save(location);

//            Double distance = LatLngTool.distance(prev, latLng, LengthUnit.MILE);
//            prev = latLng;

            count--;
        }
    }

    public Long saveLocation(Location location) {
        repository.save(location);

        return 0L;
    }
}
