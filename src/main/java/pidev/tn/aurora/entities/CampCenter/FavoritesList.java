package pidev.tn.aurora.entities.CampCenter;


import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.User.UserApp;

import javax.persistence.*;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "Fav_List")
public class FavoritesList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @OneToMany(mappedBy = "favoritesList")
    private List<CampCenter> campCenters;

    @JsonIgnore
    @OneToOne(mappedBy = "favoritesList")
    private UserApp users;
}
