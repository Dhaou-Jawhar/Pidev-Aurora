package pidev.tn.aurora.entities;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

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
    private Integer idPub;
    @Column(name = "publication")
    private String publication;


    @JsonIgnore
    @OneToMany(mappedBy = "publication", cascade = CascadeType.PERSIST)
    private List<Comment> comments = new ArrayList<>();


    @JsonIgnore
    @OneToMany(mappedBy = "publication")
    private List<Reaction> reactions = new ArrayList<>();


}