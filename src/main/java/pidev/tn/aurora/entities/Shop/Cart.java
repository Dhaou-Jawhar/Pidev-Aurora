package pidev.tn.aurora.entities.Shop;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pidev.tn.aurora.entities.Shop.Order_Produit;

import javax.persistence.*;
import java.util.Date;

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

    @OneToOne
    private Order_Produit order_produit;

}