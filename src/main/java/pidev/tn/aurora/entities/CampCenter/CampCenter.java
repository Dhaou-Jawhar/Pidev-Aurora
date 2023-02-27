package pidev.tn.aurora.entities.CampCenter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.Event.Events;
import pidev.tn.aurora.entities.User.Users;
import pidev.tn.aurora.entities.enumeration.ActivityType;
import pidev.tn.aurora.entities.enumeration.State;

import javax.persistence.*;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "camp_center")
public class CampCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "price")
    private Double price;

    @Column(name = "description")
    private String description;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

    @Enumerated(EnumType.STRING)
    State state;

    @JsonIgnore
    @OneToMany(mappedBy = "campCenter")
    Set<Review> reviews;

    @JsonIgnore
    @OneToMany(mappedBy = "campCenter")
    Set<Reservation> reservations;

    /*------[Event - CampCenter]---------*/
    @OneToOne
    @JoinColumn(name = "events_id")
    private Events events;

    @Enumerated(EnumType.STRING)
    @Column(name = "campcenter_type")
    private ActivityType campcenterType;

}