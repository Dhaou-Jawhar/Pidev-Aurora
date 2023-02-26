package pidev.tn.aurora.entities.UserApp;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Shop.Order_Produit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserApp {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "email")
    private String username;

    @Column(name = "num_tel")
    private Integer numTel;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "password")
    private String password;

    @JsonIgnore
    @OneToMany(mappedBy = "userApp")
    private List<Order_Produit> order_Produits = new ArrayList<>();

    /*------[User - Role]---------*/
    @JsonIgnore
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;


    /*------[User - Publication]---------*/
    @JsonIgnore
    @OneToMany(mappedBy = "userApp")
    private List<Publication> publications = new ArrayList<>();

    /*------[User - CampCenter]---------*/
    @JsonIgnore
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<CampCenter> campCenter;



}