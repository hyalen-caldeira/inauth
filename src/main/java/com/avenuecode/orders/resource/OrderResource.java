package com.avenuecode.orders.resource;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.service.OrderService;
import com.avenuecode.orders.specification.order.OrderSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

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

    @GetMapping(value = "/{orderId}")
    public ResponseEntity<Order> getOrder(@PathVariable String orderId) {
        Order order = orderService.getOrder(orderId);

        if (order == null)
            return notFound().build();

        return ok(order);
    }

    @RequestMapping(method = RequestMethod.GET, value = "/orders")
    @ResponseBody
    public List<Order> search(@RequestParam(value = "search") String search) {
        return orderService.searchCriteria(search);
    }
}
