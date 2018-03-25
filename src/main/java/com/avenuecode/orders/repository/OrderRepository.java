package com.avenuecode.orders.repository;

import com.avenuecode.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable>, JpaSpecificationExecutor<Order> {
}
