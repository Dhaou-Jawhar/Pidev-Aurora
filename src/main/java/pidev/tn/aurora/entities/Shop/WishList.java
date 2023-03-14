package pidev.tn.aurora.entities.Shop;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.User.UserApp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "wish_list")
public class WishList {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "createddate")
    private Date createddate;

    @JsonIgnore
    @OneToMany(mappedBy = "wishList", fetch = FetchType.EAGER)
    private List<Product> products = new ArrayList<>();
    @JsonIgnore
    @OneToOne(mappedBy = "wishList")
    private UserApp userApp;

    @JsonIgnore
    @OneToOne(mappedBy = "wishList")
    private UserApp users;
}