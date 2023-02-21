package pidev.tn.aurora.entities.Shop;

import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "price")
    private Double price;

    @Column(name = "model")
    private String model;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;


    @ManyToOne
    @JoinColumn(name = "wish_list_id")
    private WishList wishList;

}