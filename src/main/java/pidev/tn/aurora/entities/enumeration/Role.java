package pidev.tn.aurora.entities.enumeration;

import static pidev.tn.aurora.constant.Authority.*;

public enum Role {
    ROLE_COSTUMER(USER_AUTHORITIES),
    ROLE_CAMP_MANAGER(CAMP_MANAGER_AUTHORITIES),
    ROLE_SHOP_MANAGER(SHOP_MANAGER_AUTHORITIES),
    ROLE_ADMIN(ADMIN_AUTHORITIES);

    private String[] authorities;

    Role(String... authorities){
        this.authorities = authorities;
    }
    public String[] getAuthorities(){
        return authorities;
    }
}
