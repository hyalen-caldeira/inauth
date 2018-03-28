package com.avenuecode.orders.domain;

import org.springframework.data.jpa.domain.Specification;
import org.springframework.data.jpa.domain.Specifications;

import java.util.ArrayList;
import java.util.List;

public class OrderSpecificationBuilder {
    private final List<SearchCriteria> params;

    public OrderSpecificationBuilder() {
        params = new ArrayList<SearchCriteria>();
    }

    public OrderSpecificationBuilder with(String key, String operation, String value) {
        params.add(new SearchCriteria(key, operation, value));

        return this;
    }

    public OrderSpecificationBuilder with(SearchCriteria searchCriteria) {
        params.add(searchCriteria);

        return this;
    }

    public Specification<Order> build() {
        if (params.size() == 0)
            return null;

        List<Specification<Order>> specifications = new ArrayList<Specification<Order>>();
        for (SearchCriteria param : params)
            specifications.add(new OrderSpecification(param));

        Specification<Order> result = null;
        for (Specification spec : specifications) {
            result = Specifications.where(result).and(spec);
        }

        return result;
    }
}
