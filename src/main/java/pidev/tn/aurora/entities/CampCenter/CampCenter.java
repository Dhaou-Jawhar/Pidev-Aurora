package pidev.tn.aurora.entities.CampCenter;

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
@Table(name = "camp_center")
public class CampCenter {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "price")
    private Double price;

    @Column(name = "descprition")
    private String descprition;

    @Column(name = "address")
    private String address;

    @Column(name = "name")
    private String name;

}