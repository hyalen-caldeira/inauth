package us.hyalen.inauth.connection;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import org.springframework.stereotype.Component;
import us.hyalen.inauth.domain.Location;
import us.hyalen.inauth.core.location.v1.LocationResource;

import java.util.HashMap;
import java.util.Map;

@Component
public class GoogleMapsApi extends HttpConnection {
    private static final String BASE_URI_STRING = "https://maps.googleapis.com/maps/api/geocode/json";
    private static final String GET_REQUEST = "GET";

    public String getLocation (Double latitude, Double longitude) throws Exception {
        // Get JSON builder
        GsonBuilder builder = new GsonBuilder();
        Gson gson = builder.create();

        // Set initial parameters
        Map<String, String> parameters = new HashMap<>();

        // Get location
        parameters.put("latlng", latitude.toString() + "," + longitude.toString());
        parameters.put("key", "PUT YOUR KEY HERE");
        String json = getRequest(GET_REQUEST, BASE_URI_STRING, parameters);

        return json;
        // LocationResource resource = gson.fromJson(json, LocationResource.class);
        // return mapResourceToDomain(resource);
    }

    private Location mapResourceToDomain(LocationResource resource) {
        Location location = new Location();

        /*
        location.setLatitude(resource);
        location.setLongitude(resource);
        */

        return location;
    }
}
