package us.hyalen.orders.repository;

import us.hyalen.orders.domain.Order;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;
import java.math.BigDecimal;
import java.util.List;

@Repository
public interface OrderRepository extends JpaRepository<Order, Serializable>, JpaSpecificationExecutor<Order> {
    List<Order> findByProductsPriceGreaterThan(BigDecimal price);
}
