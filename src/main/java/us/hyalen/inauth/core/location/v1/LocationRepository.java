package us.hyalen.inauth.core.location.v1;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.stereotype.Repository;
import us.hyalen.inauth.domain.Location;

import java.io.Serializable;
import java.util.Optional;

@Repository
public interface LocationRepository extends JpaRepository<Location, Serializable>, JpaSpecificationExecutor<Location> {
    Optional<Location> findByLatitudeAndLongitude(Double latitude, Double longitude);

}
