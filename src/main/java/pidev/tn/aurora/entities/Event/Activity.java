package pidev.tn.aurora.entities.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "activity")
public class Activity {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private Float price;

    @Column(name = "state")
    private Boolean state;

    @Column(name = "capacity")
    private Integer capacity;

    @Column(name = "description")
    private String description;

    @Column(name = "coach_ac")
    private String coachAc;

    @Column(name = "picture_ac")
    private String pictureAc;

    @Column(name = "name_ac")
    private String nameAc;

    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "events_id")
    private Events events;

}