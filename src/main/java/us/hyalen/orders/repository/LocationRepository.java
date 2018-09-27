package us.hyalen.orders.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import us.hyalen.orders.domain.Location;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Serializable>, JpaSpecificationExecutor<Location> {
    Optional<Location> findByLatitudeAndLongitude(Double latitude, Double longitude);

}
