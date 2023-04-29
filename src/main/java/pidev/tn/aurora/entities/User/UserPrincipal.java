package pidev.tn.aurora.entities.User;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import java.util.Collection;
import java.util.stream.Collectors;

import static java.util.Arrays.stream;

public class UserPrincipal implements UserDetails {
    private UserApp userApp;

    public UserPrincipal(UserApp userApp) {
        this.userApp = userApp;
    }

    //récupérer les autorisations de l'utilisateur et de les transformer en une collection d'objets GrantedAuthority
    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return stream(this.userApp.getAuthorities()).map(SimpleGrantedAuthority::new).collect(Collectors.toList());
    }

    @Override
    public String getPassword() {
        return this.userApp.getPassword();
    }

    @Override
    public String getUsername() {
        return this.userApp.getUsername();
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return this.userApp.isNotLocked();
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return this.userApp.isActive();
    }
}
