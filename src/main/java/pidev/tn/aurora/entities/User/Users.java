package pidev.tn.aurora.entities.User;

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
public class Users {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;



    @Column(name = "email")
    private String email;

    @Column(name = "num_tel")
    private Integer numTel;

    @Column(name = "last_name")
    private String lastName;


    @Column(name = "first_name")
    private String firstName;

    @Column(name = "password")
    private String password;

    @OneToMany(mappedBy = "users")
    private List<Order_Produit> order_Produits = new ArrayList<>();

    /*------[User - Publication]---------*/
    @OneToMany(mappedBy = "users")
    private List<Publication> publications = new ArrayList<>();

    /*------[User - CampCenter]---------*/
    @ManyToMany(cascade = CascadeType.ALL)
    private Set<CampCenter> campCenter;

    /*------[User - Role]---------*/
    @ManyToOne(cascade = CascadeType.PERSIST)
    @JoinColumn(name = "role_id")
    private Role role;



}