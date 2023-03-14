package pidev.tn.aurora.entities.CampCenter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.User.UserApp;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Reservation")
public class Reservation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "name")
    private String name;

    @Column(name = "nbplace")
    private Integer nbplace ;

    @JsonIgnore
    @Column(name = "price")
    private Double price;

    @Temporal(TemporalType.DATE)
    Date dateDeb;

    @Temporal(TemporalType.DATE)
    Date dateFin;

    @JsonIgnore
    @ManyToOne
    CampCenter campCenter;

    @JsonIgnore
    @ManyToOne
    UserApp userApp;
}
