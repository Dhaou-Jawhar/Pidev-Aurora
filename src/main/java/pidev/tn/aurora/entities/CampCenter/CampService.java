package pidev.tn.aurora.entities.CampCenter;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import javax.persistence.*;
import java.util.Set;

@Entity
@Table(name = "camp_service")
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class CampService {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(name = "name")
    private String Service_name;

    @ManyToMany(mappedBy = "services")
    @JsonIgnore
    private Set <CampCenter> centers;

}