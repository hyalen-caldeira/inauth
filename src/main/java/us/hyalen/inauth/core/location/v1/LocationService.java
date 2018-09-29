package us.hyalen.inauth.core.location.v1;

import com.javadocmd.simplelatlng.LatLng;
import com.javadocmd.simplelatlng.LatLngTool;
import com.javadocmd.simplelatlng.util.LengthUnit;
import org.omg.IOP.ENCODING_CDR_ENCAPS;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import us.hyalen.inauth.domain.Location;

import java.text.DecimalFormat;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Service
public class LocationService {
    private static final String TOKYO = "TOKYO";
    private static final Double TOKYO_LATITUDE = 35.652832D;
    private static final Double TOKYO_LONGITUDE = 139.839478D;
    private static final String SYDNEY = "SYDNEY";
    private static final Double SYDNEY_LATITUDE = -33.865143D;
    private static final Double SYDNEY_LONGITUDE = 151.209900D;
    private static final String RIYADH = "RIYADH";
    private static final Double RIYADH_LATITUDE = 24.774265D;
    private static final Double RIYADH_LONGITUDE = 46.738586D;
    private static final String ZURICH = "ZURICH";
    private static final Double ZURICH_LATITUDE = 47.373878D;
    private static final Double ZURICH_LONGITUDE = 8.545094D;
    private static final String REYKJAVIK = "REYKJAVIK";
    private static final Double REYKJAVIK_LATITUDE = 64.128288D;
    private static final Double REYKJAVIK_LONGITUDE = -21.827774D;
    private static final String MEXICO = "MEXICO";
    private static final Double MEXICO_LATITUDE = 19.42847D;
    private static final Double MEXICO_LONGITUDE = -99.12766D;
    private static final String LIMA = "LIMA";
    private static final Double LIMA_LATITUDE = -12.04318D;
    private static final Double LIMA_LONGITUDE = -77.02824D;

    @Autowired
    private LocationRepository repository;

    public List<Location> getAllLocation() {
        return repository.findAll();
    }

    public Optional<Location> getLocationByLatitudeAndLongitude(Double latitude, Double longitude) {
        return repository.findByLatitudeAndLongitude(latitude, longitude);
    }

    public Long saveLocation(Location location) {
        repository.save(location);

        return 0L;
    }

    public String getAssessment(Double latitude, Double longitude) {
        Map<String, LatLng> map = getAssessmentMap(latitude, longitude);
        StringBuilder sb = new StringBuilder();
        DecimalFormat df = new DecimalFormat(".##");

        if (isWithinUsa(latitude, longitude))
            sb.append("The given coordinate is withing the USA");
        else {
            sb.append("The given coordinate has: \n");

            for (Map.Entry<String, LatLng> entry : map.entrySet()) {
                String city = entry.getKey();
                LatLng latLng = entry.getValue();

                Double distance = distance(latitude, latLng.getLatitude(), longitude, latLng.getLongitude(), 0, 0);

                distance = Double.valueOf(df.format(distance));

                sb.append("\n").append(distance).append(" MILES of distance from ").append(city);

                if (distance <= 500D)
                    sb.append(" and it is also within 500 MILES of distance.");
            }
        }

        return sb.toString();
    }

    private Map<String, LatLng> getAssessmentMap(Double latitude, Double longitude) {
        Map<String, LatLng> map = new HashMap<>();

        map.put(TOKYO, new LatLng(TOKYO_LATITUDE, TOKYO_LONGITUDE));
        map.put(SYDNEY, new LatLng(SYDNEY_LATITUDE, SYDNEY_LONGITUDE));
        map.put(RIYADH, new LatLng(RIYADH_LATITUDE, RIYADH_LONGITUDE));
        map.put(ZURICH, new LatLng(ZURICH_LATITUDE, ZURICH_LONGITUDE));
        map.put(REYKJAVIK, new LatLng(REYKJAVIK_LATITUDE, REYKJAVIK_LONGITUDE));
        map.put(MEXICO, new LatLng(MEXICO_LATITUDE, MEXICO_LONGITUDE));
        map.put(LIMA, new LatLng(LIMA_LATITUDE, LIMA_LONGITUDE));

        return map;
    }

    private double distance(double lat1, double lat2, double lon1,
                                  double lon2, double el1, double el2) {

        final int R = 6371; // Radius of the earth

        double latDistance = Math.toRadians(lat2 - lat1);
        double lonDistance = Math.toRadians(lon2 - lon1);
        double a = Math.sin(latDistance / 2) * Math.sin(latDistance / 2)
                + Math.cos(Math.toRadians(lat1)) * Math.cos(Math.toRadians(lat2))
                * Math.sin(lonDistance / 2) * Math.sin(lonDistance / 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = R * c * 0.621371; // convert to miles

        double height = el1 - el2;

        distance = Math.pow(distance, 2) + Math.pow(height, 2);

        return Math.sqrt(distance);
    }

    private boolean isWithinUsa(double latitude, double longitude) {
        double top = 49.3457868D; // # north lat
        double left = -124.7844079D; // # west long
        double right = -66.9513812D; // # east long
        double bottom = 24.7433195D; // # south lat

        return bottom <= latitude && latitude <= top && left <= longitude && longitude <= right;
    }
}
