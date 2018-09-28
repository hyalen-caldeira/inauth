package us.hyalen.inauth.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import us.hyalen.inauth.connection.GoogleMapsApi;
import us.hyalen.inauth.domain.Location;
import us.hyalen.inauth.service.LocationService;

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

    // TODO, how to get 2 values
    @GetMapping(value = "/api/locations/{latitude:.+},{longitude:.+}")
    public ResponseEntity<String> getData(@PathVariable Double latitude, @PathVariable Double longitude) throws Exception {
        Optional<Location> location = locationService.getLocationByLatitudeAndLongitude(latitude, longitude);

        if (!location.isPresent())
            return notFound().build();

        return ok(mapsApi.getLocation(location.get().getLatitude(), location.get().getLongitude()));

//        return ok(location.get());
    }

    // TODO, use correct parameter format
    // TODO, return locationId
    @PostMapping(value = "/api/locations")
    public void addData(String latitudeLongitude) {
        /*
        Acessar API Google e pegar os dados corretos
        Atribuir valores pro objeto
        Salvar o objeto
        */
    }
}
