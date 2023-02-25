package pidev.tn.aurora.entities.Event;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pidev.tn.aurora.entities.Event.Activity;

import javax.persistence.*;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

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

    @JsonIgnore
    @OneToMany(mappedBy = "wishListEv", cascade = CascadeType.PERSIST)
    private Set<Activity> activities = new HashSet<>();

}