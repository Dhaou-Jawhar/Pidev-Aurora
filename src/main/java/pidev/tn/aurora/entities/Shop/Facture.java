package pidev.tn.aurora.entities.Shop;

import lombok.*;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.enumeration.FactureType;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "facture")
public class Facture {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Temporal(TemporalType.DATE)
    @Column(name = "date")
    private Date date;

    @Column(name = "price")
    private Double price;

    @Column(name = "number")
    private Long number;


    @Column(name = "state")
    private Boolean state;

    @Enumerated(EnumType.STRING)
    @Column(name = "facture_type")
    private FactureType factureType;

    @OneToOne(mappedBy = "facture")
    private Order_Produit order_produit;


}