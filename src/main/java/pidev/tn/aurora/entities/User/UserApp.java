package pidev.tn.aurora.entities.User;

import lombok.*;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.Shop.WishList;
import pidev.tn.aurora.entities.enumeration.Role;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

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

    @Enumerated(EnumType.STRING)
    @Column(name = "role")
    private Role role;

    @Column(name = "email")
    private String email;

    @Column(name = "num_tel")
    private Integer numTel;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "discount")
    private Integer discount;

    @OneToMany(mappedBy = "userApp")
    private List<Order_Produit> order_Produits = new ArrayList<>();

    /*------[User - Publication]---------*/
    @OneToMany(mappedBy = "userApp")
    private List<Publication> publications = new ArrayList<>();

    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wish_list_id")
    private WishList wishList;

}