package pidev.tn.aurora.entities.Forum;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import pidev.tn.aurora.entities.User.UserApp;

import javax.persistence.*;
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter

@Entity
@Table(name = "message")
public class Message {

    public Message(String contenu, UserApp sender){
        this.contenu = contenu;
        this.sender = sender;
    }
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

    private String contenu;

    @ManyToOne
    @JoinColumn(name = "users_id")
    private UserApp sender;


}