package us.hyalen.inauth.controller;

import us.hyalen.inauth.domain.Order;
import us.hyalen.inauth.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

import static org.springframework.http.ResponseEntity.notFound;
import static org.springframework.http.ResponseEntity.ok;

@RestController
@RequestMapping("/orders")
public class OrderResource {
    @Autowired
    private OrderService orderService;

    @GetMapping
    public ResponseEntity<List<Order>> listOrders() {
        return ok(orderService.listOrders());
    }

    @GetMapping(value = "/{orderId:\\d+}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);

        if (order == null)
            return notFound().build();

        return ok(order);
    }
}
