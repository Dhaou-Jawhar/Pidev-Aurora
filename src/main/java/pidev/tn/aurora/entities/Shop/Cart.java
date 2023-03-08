package pidev.tn.aurora.entities.Shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.Shop.Order_Produit;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

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

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "order_produit_id")
    private Order_Produit order_Produit;

    @OneToMany(mappedBy = "cart", cascade = CascadeType.PERSIST)
    private List<CartItems> cartItemses = new ArrayList<>();

}