package pidev.tn.aurora.entities.User;

import pidev.tn.aurora.entities.enumeration.TypeRole;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "role")
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @Column(name = "typeRole")
    @Enumerated(EnumType.STRING)
    private TypeRole typeRole;

    /*------[User - Role]---------*/

    @OneToMany(mappedBy = "role", cascade = CascadeType.PERSIST)
    private List<Users> users = new ArrayList<>();


}