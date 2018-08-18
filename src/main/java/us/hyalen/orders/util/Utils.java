package us.hyalen.orders.util;

import us.hyalen.orders.domain.Order;

import java.util.ArrayList;
import java.util.List;

public class Utils {
    public static List<Order> fetchOrdersByNumberOfValidProducts(List<Order> orders, int size) {
    	List<Order> list = new ArrayList<Order>();

    	if (orders == null || orders.size() == 0)
    		return list;

    	for (Order order : orders)
    		if (order.getProducts().size() >= size)
				list.add(order);

    	return list;
    }
}
