package pidev.tn.aurora.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.*;

import javax.persistence.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Entity
@Table(name = "comment")
public class Comment {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "idComment", nullable = false)
    private Integer idComment;
    @Column(name = "comment")
    private String comment;
    @JsonIgnore
    @ManyToOne
    @JoinColumn(name = "publication_id")
    private Publication publication;

}