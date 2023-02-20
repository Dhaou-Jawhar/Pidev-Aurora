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
    private String price;

    @Column(name = "manager_ev")
    private String managerEv;

    @Column(name = "picture_ev")
    private String pictureEv;

    @Column(name = "name_ev")
    private String nameEv;

}