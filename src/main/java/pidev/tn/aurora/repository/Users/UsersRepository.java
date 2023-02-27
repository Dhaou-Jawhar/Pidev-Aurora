package pidev.tn.aurora.repository.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.User.UserApp;
import java.util.List;

public interface UsersRepository extends JpaRepository<UserApp, Integer> {

    List<UserApp> findAll();

    UserApp findByUsername(String username);

}