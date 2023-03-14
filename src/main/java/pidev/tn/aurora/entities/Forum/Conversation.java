package pidev.tn.aurora.entities.Forum;

import com.fasterxml.jackson.annotation.JsonIgnore;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pidev.tn.aurora.entities.User.UserApp;

import javax.persistence.*;
import java.util.ArrayList;
import java.util.List;

@Entity
@Table(name = "conversation")
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
public class Conversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserApp userApp;

    @JsonIgnore
    @ManyToMany(mappedBy = "conversations", cascade = CascadeType.PERSIST)
    private List<UserApp> participants = new ArrayList<>();

    @OneToMany(cascade = CascadeType.ALL, mappedBy = "id")
    private List<Message> messages = new ArrayList<>();
}