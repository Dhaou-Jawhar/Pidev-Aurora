package pidev.tn.aurora.entities.Shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.enumeration.Cat;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "product")
public class Product {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id")
    private Integer id;

    @Column(name = "price")
    private Double price;

    @Column(name = "model")
    private String model;

    @Column(name = "description")
    private String description;

    @Column(name = "name")
    private String name;

    @JsonIgnore
    @Enumerated(EnumType.STRING)
    @Column(name = "cat")
    private Cat cat;

    /*----------[Product -> WishList]-----------*/
    @JsonIgnore
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wish_list_id")
    private WishList wishList;

    /*----------[Product -> Cart Items]-----------*/
    @OneToMany(mappedBy = "product")
    private List<CartItems> cartItemses = new ArrayList<>();


    public static String[] fields() {
        return new String[]{"id", "description", "model", "name", "price"};
    }


}