package com.avenuecode.orders.service;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.repository.OrderRepository;
import com.avenuecode.orders.specification.order.OrderSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

@Service
public class OrderService {

    @Autowired
    private OrderRepository orderRepository;

    public List<Order> listOrders() {
        return orderRepository.findAll();
    }

    public Order getOrder(String orderId) {
        return orderRepository.findOne(orderId);
    }

    public List<Order> searchCriteria(String search) {
        OrderSpecificationBuilder builder = new OrderSpecificationBuilder();
        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(search + ",");

        while (matcher.find())
            builder.with(matcher.group(1), matcher.group(2), matcher.group(3));

        Specification<Order> spec = builder.build();

        return orderRepository.findAll(spec);
    }
}
