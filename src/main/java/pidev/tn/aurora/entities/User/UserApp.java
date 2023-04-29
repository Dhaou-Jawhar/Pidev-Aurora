package pidev.tn.aurora.entities.User;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonIgnore;
import org.hibernate.annotations.LazyCollection;
import org.hibernate.annotations.LazyCollectionOption;
import pidev.tn.aurora.entities.Forum.Comment;
import pidev.tn.aurora.entities.Forum.Conversation;
import pidev.tn.aurora.entities.CampCenter.FavoritesList;

import pidev.tn.aurora.entities.CampCenter.Reservation;
import lombok.*;
import pidev.tn.aurora.entities.Event.Activity;
import pidev.tn.aurora.entities.Event.Rating;
import pidev.tn.aurora.entities.Forum.Publication;
import pidev.tn.aurora.entities.Shop.Cart;
import pidev.tn.aurora.entities.Forum.Reaction;
import pidev.tn.aurora.entities.Shop.Order_Produit;
import pidev.tn.aurora.entities.Shop.WishList;

import javax.persistence.*;
import java.io.Serializable;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Set;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "users")
public class UserApp implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "username")
    private String username;

    @Column(name = "num_tel")
    private Integer numTel;

    @Column(name = "last_name")
    private String lastName;

    @Column(name = "first_name")
    private String firstName;

    @Column(name = "discount")
    private Integer discount;

    @Column(name = "password")
    private String password;

    @Column(name = "email")
    private String email;

    @Column(name = "profileImageUrl")
    private String profileImageUrl;

    @Column(name = "LastLoginDate")
    private Date LastLoginDate;

    @Column(name = "LastLoginDateDisplay")
    private Date LastLoginDateDisplay;

    @Column(name = "joinDate")
    @JsonFormat(pattern = "dd/MM/yyyy HH:mm:ss")
    private Date joinDate;

    //@Column(name = "joinDate")
    private String role;
    private String[] authorities;

    @Column(name = "isActive")
    private boolean isActive;

    @Column(name = "isNotLocked")
    private boolean isNotLocked;

    @JsonIgnore
    @OneToMany(mappedBy = "userApp")
    private List<Order_Produit> order_Produits = new ArrayList<>();
    /*------[User - Role]---------*/
  // @JsonIgnore
   //@ManyToOne(cascade = CascadeType.PERSIST)
   //@JoinColumn(name = "role_id")
    //private Role roles;

    /*------[User - Publication]---------*/
    @JsonIgnore
    @OneToMany(mappedBy = "userApp")
    private List<Publication> publications = new ArrayList<>();

    /*------[User - Reservation]---------*/
    @JsonIgnore
    @OneToMany(mappedBy = "userApp")
    Set<Reservation> reservations;

    /*----------[User -> Wishlist]-----------*/
    @JsonIgnore
    @OneToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "wish_list_id")
    private WishList wishList;

    /*----------[User -> Cart]-----------*/
    @JsonIgnore
    @OneToMany(mappedBy = "userApp")
    private List<Cart> cart;

    
    //////////////////user---comment/////////////////////
    @OneToMany(mappedBy = "user", cascade = CascadeType.ALL)
    @JsonIgnore
    private List<Comment> comments;
    ////////////////user---reaction/////////////////////
    @JsonIgnore
    @OneToOne(mappedBy = "user")
    private Reaction reaction;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "users_conversation",
            joinColumns = @JoinColumn(name = "user_id"),
            inverseJoinColumns = @JoinColumn(name = "conversation_id"))
    private List<Conversation> conversations = new ArrayList<>();

    public List<Conversation> getConversations() {
        return conversations;
    }

    public void setConversations(List<Conversation> conversations) {
        this.conversations = conversations;
    }

    public Integer getId() {
        return id;
    }

    /*------[User - FavoritesList]---------*/
    @JsonIgnore
    @OneToOne(cascade = CascadeType.ALL)
    private FavoritesList favoritesList;

    @JsonIgnore
    @ManyToMany
    @JoinTable(name = "users_activities",
            joinColumns = @JoinColumn(name = "user_app_id"),
            inverseJoinColumns = @JoinColumn(name = "activities_id"))
    private List<Activity> activities = new ArrayList<>();

    @JsonIgnore
    @OneToMany(mappedBy = "userApp")
    private List<Rating> ratings = new ArrayList<>();

}