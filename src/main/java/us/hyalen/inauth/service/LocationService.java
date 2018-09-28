package us.hyalen.inauth.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.hyalen.inauth.domain.Location;
import us.hyalen.inauth.repository.LocationRepository;

import java.util.List;
import java.util.Optional;

@Service
public class LocationService {
    @Autowired
    private LocationRepository locationRepository;

    public List<Location> getAllLocation() {
        return locationRepository.findAll();
    }

    public Optional<Location> getLocationByLatitudeAndLongitude(Double latitude, Double longitude) {
        return locationRepository.findByLatitudeAndLongitude(latitude, longitude);
    }

    public Long saveLocation(Location location) {
        locationRepository.save(location);

        return 0L;
    }
}
