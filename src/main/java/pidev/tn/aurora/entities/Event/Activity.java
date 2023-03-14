package pidev.tn.aurora.entities.Event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ActivityType;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "activity_type")
    private ActivityType activityType;

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
    @ManyToMany(mappedBy = "activities", cascade = CascadeType.PERSIST)
    private List<UserApp> userApps = new ArrayList<>();

    @Column(name = "participant")
    private Integer participant;

    @ManyToMany(mappedBy = "activities", cascade = CascadeType.PERSIST)
    private List<WishListEv> wishListEvs = new ArrayList<>();

}