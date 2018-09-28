package us.hyalen.inauth.repository;

import us.hyalen.inauth.domain.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;

import java.io.Serializable;

@Repository
public interface ProductRepository extends JpaRepository<Product, Serializable>, JpaSpecificationExecutor<Product> {
}
