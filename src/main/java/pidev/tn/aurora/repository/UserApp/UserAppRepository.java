package pidev.tn.aurora.repository.UserApp;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.UserApp.UserApp;

public interface UserAppRepository extends JpaRepository<UserApp, Integer> {
    UserApp findByUsername(String username );
}