package com.avenuecode.orders.specification.order;

import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Predicate;
import javax.persistence.criteria.Root;

import com.avenuecode.orders.specification.SearchCriteria;
import org.springframework.data.jpa.domain.Specification;

import com.avenuecode.orders.domain.Order;

public class OrderSpecification implements Specification<Order> {
	private SearchCriteria criteria;

	public OrderSpecification(SearchCriteria criteria) {
	    this.criteria = criteria;
    }
	
	@Override
	public Predicate toPredicate(Root<Order> root, CriteriaQuery<?> query, CriteriaBuilder builder) {
		if (criteria.getOperation().equalsIgnoreCase(">")) {
            return builder.greaterThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase("<")) {
            return builder.lessThanOrEqualTo(
              root.<String> get(criteria.getKey()), criteria.getValue().toString());
        } else if (criteria.getOperation().equalsIgnoreCase(":")) {
            if (root.get(criteria.getKey()).getJavaType() == String.class) {
                return builder.like(
                  root.<String>get(criteria.getKey()), "%" + criteria.getValue() + "%");
            } else {
                return builder.equal(root.get(criteria.getKey()), criteria.getValue());
            }
        }
		
        return null;
	}
}
