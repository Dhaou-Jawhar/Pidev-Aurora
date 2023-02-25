package pidev.tn.aurora.entities.CampCenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pidev.tn.aurora.entities.CampCenter.CampCenter;
import pidev.tn.aurora.entities.enumeration.Note;

import javax.persistence.*;
import java.util.Date;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "review_camp")
public class Review {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "comment")
    private String comment;

    @Column(name = "date")
    @Temporal(TemporalType.DATE)
    Date date;

    @Column(name = "note")
    @Enumerated(EnumType.STRING)
    Note note;

    @ManyToOne
    CampCenter campCenter;

}
