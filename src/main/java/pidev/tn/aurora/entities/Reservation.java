package pidev.tn.aurora.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Column(name = "price")
    private Double price;

    @Temporal(TemporalType.DATE)
    Date dateDeb;

    @Temporal(TemporalType.DATE)
    Date dateFin;

    @ManyToOne
    CampCenter campCenter;
}
