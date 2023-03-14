package pidev.tn.aurora.entities.Shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import org.springframework.beans.factory.annotation.Autowired;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.repository.Shop.ProductRepository;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Optional;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart")
public class Cart {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "createdat")
    private Date createdat;

    @Column(name = "quantity")
    private Integer quantity;

    @Column(name = "totalprice")
    private Double totalprice;

    @Column(name ="active")
    private boolean active = true;


    /*----------[Cart -> Cart Items]-----------*/
    @JsonIgnore
    @OneToMany(mappedBy = "cart", cascade = CascadeType.ALL, orphanRemoval = true)
    private List<CartItems> items = new ArrayList<>();

    /*----------[Cart -> User]-----------*/
    @ManyToOne
    @JoinColumn(name = "user_app_id")
    private UserApp userApp;

    /*----------[Cart -> Order]-----------*/
    @OneToOne
    @JoinColumn(name = "order_produit_id")
    private Order_Produit order_Produit;


}