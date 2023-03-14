package pidev.tn.aurora.entities.Event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.Event.Activity;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "events")
public class Events {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;


    @Column(name = "description_ev")
    private String descriptionEv;

    @Temporal(TemporalType.DATE)
    @Column(name = "date_ev")
    private Date dateEv;

    @Column(name = "price_ev")
    private Float priceEv;

    @Column(name = "manager")
    private String manager;

    @Column(name = "picture_ev")
    private String pictureEv;

    @Column(name = "name_ev")
    private String nameEv;

    @JsonIgnore
    @OneToMany(mappedBy = "events",fetch = FetchType.EAGER)
    private List<Activity> activities = new ArrayList<>();

    /*------[Event - CampCenter]---------*/
    @JsonIgnore
    @OneToOne(mappedBy = "events")
    private CampCenter campCenter;

    @OneToMany(mappedBy = "events")
    private List<Rating> ratings = new ArrayList<>();

}