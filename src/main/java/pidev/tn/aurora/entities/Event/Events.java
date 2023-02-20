package pidev.tn.aurora.entities.Event;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import javax.persistence.*;
import java.util.Date;

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

}