package pidev.tn.aurora.repository.Users;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.User.Users;

public interface UsersRepository extends JpaRepository<Users, Integer> {
}