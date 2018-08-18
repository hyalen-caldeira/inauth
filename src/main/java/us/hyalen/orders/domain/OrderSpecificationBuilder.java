package us.hyalen.orders.domain;

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

        List<Specification<Order>> specs = new ArrayList<Specification<Order>>();

        for (SearchCriteria param : params)
            specs.add(new OrderSpecification(param));

        Specification<Order> result = specs.get(0);

        for (int i = 1; i < specs.size(); i++)
            result = Specifications.where(result).and(specs.get(i));

        return result;
    }
}
