package pidev.tn.aurora.repository.UserApp;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import pidev.tn.aurora.entities.User.UserApp;

import java.util.List;

public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    UserApp findByUsername(String username );
}