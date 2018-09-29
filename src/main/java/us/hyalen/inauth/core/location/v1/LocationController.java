package us.hyalen.inauth.core.location.v1;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.hyalen.inauth.connection.GoogleMapsApi;
import us.hyalen.inauth.domain.Location;

import java.util.List;
import java.util.Optional;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/inauth")
public class LocationController {
    @Autowired
    private LocationService locationService;

    @Autowired
    private GoogleMapsApi mapsApi;

    @GetMapping(value = "/api/locations")
    public ResponseEntity<List<Location>> getAllDataSets() {
        return ok(locationService.getAllLocation());
    }

    @GetMapping(value = "/api/locations/{latitude:.+},{longitude:.+}")
    public ResponseEntity<String> getData(@PathVariable Double latitude, @PathVariable Double longitude) throws Exception {
        Optional<Location> location = locationService.getLocationByLatitudeAndLongitude(latitude, longitude);

        // Return not found if coordinate doesn't exist in the DB
        if (!location.isPresent())
            return notFound().build();

        // Otherwise return the geocoding information from Google Maps API
        return ok(mapsApi.getLocation(location.get().getLatitude(), location.get().getLongitude()));
    }

    @PostMapping(value = "/api/locations")
    public void addData(@RequestBody Location location) {
        locationService.saveLocation(location);
    }

    @GetMapping(value = "/api/assessment/{latitude:.+},{longitude:.+}")
    public ResponseEntity<String> getAssessment(@PathVariable Double latitude, @PathVariable Double longitude) throws Exception {

        return ok(locationService.getAssessment(latitude, longitude));
    }
}
