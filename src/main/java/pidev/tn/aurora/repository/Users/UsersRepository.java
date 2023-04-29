package pidev.tn.aurora.repository.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import pidev.tn.aurora.entities.User.UserApp;
import java.util.List;

public interface UsersRepository extends JpaRepository<UserApp, Integer> {

    @Query("UPDATE UserApp u SET u.username = :username, u.numTel = :numTel, u.lastName = :lastName, u.firstName = :firstName, u.password = :password WHERE u.id = :id")
    void update(@Param("username") String username, @Param("numTel") Integer numTel, @Param("lastName") String lastName, @Param("firstName") String firstName);
    List<UserApp> findAll();

    UserApp findByUsername(String username);

    UserApp findUserByEmail(String email );


}