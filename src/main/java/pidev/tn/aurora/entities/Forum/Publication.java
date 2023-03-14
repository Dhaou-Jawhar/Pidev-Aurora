package pidev.tn.aurora.entities.Forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;
import pidev.tn.aurora.entities.User.UserApp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "publication")
public class Publication {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;
    @Column(name = "publication")
    private String publication;


    @JsonIgnore
    @OneToMany(mappedBy = "publication", cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "publication")
    private List<Reaction> reactions = new ArrayList<>();

    /*------[User - Publication]---------*/
    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserApp userApp;

}