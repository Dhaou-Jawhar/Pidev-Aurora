package pidev.tn.aurora.entities.Shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.User.UserApp;
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

    /*----------[Order -> Cart]-----------*/

    @OneToOne(mappedBy = "order_Produit")
    private Cart cart;

    /*----------[Order -> Facture]-----------*/
    @JsonIgnore
    @OneToOne
    private Facture facture;
    /*----------[Order -> User]-----------*/
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserApp userApp;



}