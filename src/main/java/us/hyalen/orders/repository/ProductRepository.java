package us.hyalen.orders.repository;

import us.hyalen.orders.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable>, JpaSpecificationExecutor<Product> {
}
