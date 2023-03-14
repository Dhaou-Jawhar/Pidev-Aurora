package pidev.tn.aurora.entities.Forum;

import pidev.tn.aurora.entities.User.UserApp;

import javax.persistence.*;

@Entity
@Table(name = "users_conversation")
public class UserConversation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "id", nullable = false)
    private Integer id;

    @ManyToOne
    @JoinColumn(name = "user_id")
    private UserApp user;

    @ManyToOne
    @JoinColumn(name = "conversation_id")
    private Conversation conversation;

}