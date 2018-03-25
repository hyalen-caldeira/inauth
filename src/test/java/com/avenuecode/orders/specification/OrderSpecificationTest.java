package com.avenuecode.orders.specification;

import com.avenuecode.orders.Application;
import com.avenuecode.orders.domain.Order;
import com.avenuecode.orders.repository.OrderRepository;
import com.avenuecode.orders.specification.order.OrderSpecification;
import org.junit.Before;
import org.junit.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.web.WebAppConfiguration;

import java.util.List;

import static org.hamcrest.collection.IsIn.isIn;
import static org.junit.Assert.assertThat;

@SpringBootTest
@ContextConfiguration(classes = {Application.class})
@WebAppConfiguration
public class OrderSpecificationTest {
    @Autowired
    private OrderRepository repository;

    @Before
    public void init() {
    }

    @Test
    public void givenLast_whenGettingListOfUsers_thenCorrect() {
        OrderSpecification spec =
            new OrderSpecification(new SearchCriteria("lastName", ":", "doe"));

        List<Order> results = repository.findAll(spec);

        // assertThat(orderX, isIn(results));
        // assertThat(orderY, isIn(results));
    }
}
