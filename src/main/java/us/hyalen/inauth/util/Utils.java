package us.hyalen.inauth.util;

import com.javadocmd.simplelatlng.LatLng;
import org.springframework.beans.factory.annotation.Autowired;
import us.hyalen.inauth.connection.GoogleMapsApi;
import us.hyalen.inauth.domain.Location;
import us.hyalen.inauth.domain.Order;
import us.hyalen.inauth.repository.LocationRepository;

import java.util.ArrayList;
import java.util.List;

public class Utils {
	@Autowired
	LocationRepository repository;

    public static List<Order> fetchOrdersByNumberOfValidProducts(List<Order> orders, int size) {
    	List<Order> list = new ArrayList<Order>();

    	if (orders == null || orders.size() == 0)
    		return list;

    	for (Order order : orders)
    		if (order.getProducts().size() >= size)
				list.add(order);

    	return list;
    }

	public void populate() throws Exception {
		GoogleMapsApi mapsApi = new GoogleMapsApi();

		int count = 10;

		while (count > 0) {
			LatLng latLng = LatLng.random();

			Location location = mapsApi.getLocation(latLng.toString());

			repository.save(location);
		}
	}
}
