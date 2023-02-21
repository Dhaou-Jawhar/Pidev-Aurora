package pidev.tn.aurora.entities;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private String totalprice;

    @Temporal(TemporalType.DATE)
    @Column(name = "createddate")
    private Date createddate;

    @OneToMany(mappedBy = "order_Produit")
    private List<Product> products = new ArrayList<>();

    @OneToOne
    private Facture facture;

    @OneToOne(mappedBy = "order_produit")
    private Cart cart;

}