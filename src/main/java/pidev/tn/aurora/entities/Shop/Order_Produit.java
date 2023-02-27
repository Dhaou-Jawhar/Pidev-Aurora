package pidev.tn.aurora.entities.Shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.User.Users;
import pidev.tn.aurora.entities.enumeration.PaymentMethod;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "order_produit")
public class Order_Produit {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "totalprice")
    private Double totalprice;


    @Enumerated(EnumType.STRING)
    @Column(name = "payment_method")
    private PaymentMethod paymentMethod;

    @Temporal(TemporalType.DATE)
    @Column(name = "createddate")
    private Date createddate;

    @OneToOne
    private Facture facture;
    @JsonIgnore
    @OneToMany(mappedBy = "order_Produit")
    private List<Cart> carts = new ArrayList<>();

    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "users_id")
    private Users users;

}