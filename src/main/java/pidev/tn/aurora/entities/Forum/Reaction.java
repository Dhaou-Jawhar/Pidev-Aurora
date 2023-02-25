package pidev.tn.aurora.entities.Forum;

import lombok.*;
import pidev.tn.aurora.entities.Forum.Publication;
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
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idReaction", nullable = false)
    private Integer idReaction;
    @Enumerated(EnumType.STRING)
    @Column(name = "reactType")
    private ReactType reactType;

    @Getter(AccessLevel.NONE)
    @Setter(AccessLevel.NONE)
    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;


}