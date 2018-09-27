package us.hyalen.orders.domain;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import lombok.Data;

import javax.persistence.*;

import static com.fasterxml.jackson.annotation.JsonInclude.Include.NON_NULL;
import static javax.persistence.GenerationType.IDENTITY;

@Data
@Entity
@Table(name = "location")
@JsonInclude(NON_NULL)
public class Location {
    @Id
    @GeneratedValue(strategy = IDENTITY)
    @JsonIgnore
    private Long locationId;

    @Column(nullable = false)
    private Double latitude;

    @Column(nullable = false)
    private Double longitude;

    @Column(nullable = false, length = 40)
    private String city;

    @Column(nullable = false, length = 40)
    private String state;

    @Column(nullable = false, length = 40)
    private String country;
}
