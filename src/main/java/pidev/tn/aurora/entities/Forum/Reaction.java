package pidev.tn.aurora.entities.Forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pidev.tn.aurora.entities.User.UserApp;
import pidev.tn.aurora.entities.enumeration.ReactType;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "reaction")
public class Reaction {
    @Id
    @JsonIgnore
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReaction", nullable = false)
    private Integer idReaction;
    @Enumerated(EnumType.STRING)
    @Column(name = "reactType")
    private ReactType reactType;

    @ManyToOne
    @JsonIgnore
    @JoinColumn(name = "publication_id")
    private Publication publication;
    @OneToOne
    @JsonIgnore
    @JoinColumn(name = "user_id")
    private UserApp user;


}