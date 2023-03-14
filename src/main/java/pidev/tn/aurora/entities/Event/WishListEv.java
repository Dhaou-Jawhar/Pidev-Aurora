package pidev.tn.aurora.entities.Event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pidev.tn.aurora.entities.Event.Activity;

import javax.persistence.*;
import java.util.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wish_list_ev")
public class WishListEv {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_date")
    private Date created_date;

    @ManyToMany
    @JoinTable(name = "wish_list_ev_activities",
            joinColumns = @JoinColumn(name = "wish_list_ev_id"),
            inverseJoinColumns = @JoinColumn(name = "activities_id"))
    private List<Activity> activities = new ArrayList<>();

}