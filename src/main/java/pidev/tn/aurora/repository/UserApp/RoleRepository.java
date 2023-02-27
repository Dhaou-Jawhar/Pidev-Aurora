package pidev.tn.aurora.repository.UserApp;

import org.springframework.data.jpa.repository.JpaRepository;
import pidev.tn.aurora.entities.User.Role;
import pidev.tn.aurora.entities.enumeration.TypeRole;

public interface RoleRepository extends JpaRepository<Role, Integer> {
    Role getRoleByTypeRole(TypeRole typeRole);
}