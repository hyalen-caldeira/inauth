package com.avenuecode.orders.service;

import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.specification.SearchCriteria;
import com.avenuecode.orders.repository.OrderRepository;
import com.avenuecode.orders.specification.OrderSpecificationBuilder;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.domain.Specification;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

@Service
public class SearchService {
    @Autowired
    private OrderRepository orderRepository;

    public List<Order> searchCriteria(String criteria) {
        OrderSpecificationBuilder builder = new OrderSpecificationBuilder();

        Pattern pattern = Pattern.compile("(\\w+?)(:|<|>)(\\w+?),");
        Matcher matcher = pattern.matcher(criteria + ",");

        while (matcher.find()) {
            SearchCriteria searchCriteria = new SearchCriteria(matcher.group(1), matcher.group(2), matcher.group(3));

            if(searchCriteria.getKey() != null)
                if(searchCriteria.getKey().equals("price"))
                    return fetchOrdersByProductPrice(new BigDecimal(searchCriteria.getValue()));
                else if(searchCriteria.getKey().equals("products"))
                    return fetchOrdersByNumberOfValidProducts(Integer.parseInt(searchCriteria.getValue()));
                else
                    builder.with(searchCriteria);
        }

        return fetchOrdersByCriteria(builder.build());
    }

    public List<Order> fetchOrdersByCriteria(Specification<Order> spec) {
        return orderRepository.findAll(spec);
    }

    public List<Order> fetchOrdersByProductPrice(BigDecimal price) {
        return orderRepository.findByProductsPriceGreaterThan(price).stream().distinct().collect(Collectors.toList());
    }

    public List<Order> fetchOrdersByNumberOfValidProducts(int size) {
        List<Order> list = new ArrayList<Order>();

        List<Order> orders = orderRepository
            .findByProductsPriceGreaterThan(new BigDecimal("0"))
            .stream().distinct().collect(Collectors.toList());

        if (orders == null || orders.size() == 0)
            return list;

        for (Order order : orders)
            if (order.getProducts().size() >= size)
                list.add(order);

        return list;
    }
}