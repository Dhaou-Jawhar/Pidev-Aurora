package pidev.tn.aurora.entities.Shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "cart_items")
public class CartItems {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "quantity")
    private Integer quantity;

    @Temporal(TemporalType.DATE)
    @Column(name = "created_at")
    private Date createdAt;

    /*----------[Cart Items -> Product]-----------*/
    @ManyToOne
    @JoinColumn(name = "product_id")
    private Product product;

    /*----------[Cart Items -> Cart]-----------*/
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "cart_id")
    private Cart cart;



}