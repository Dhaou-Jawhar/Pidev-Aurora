package pidev.tn.aurora.entities.CampCenter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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


}