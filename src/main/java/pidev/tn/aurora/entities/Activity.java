package pidev.tn.aurora.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
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
    @Column(name = "id", nullable = false)
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
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "events_id")
    private Events events;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "wish_list_ev_id")
    private WishListEv wishListEv;

}