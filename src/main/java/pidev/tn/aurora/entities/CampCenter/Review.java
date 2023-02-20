package pidev.tn.aurora.entities.CampCenter;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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

    @Temporal(TemporalType.DATE)
    Date date;

    @Enumerated(EnumType.STRING)
    Note note;

    @ManyToOne
    CampCenter campCenter;

}
