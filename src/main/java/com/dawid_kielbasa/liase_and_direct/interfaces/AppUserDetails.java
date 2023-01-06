package com.dawid_kielbasa.liase_and_direct.interfaces;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

/**
 * Interface that provide methods for AppUser class which implements this interface. It is basically UserDetails interface provided in framework,
 * but extended for one more String value. Using extend make me more trouble that just copying code.
 */
public interface AppUserDetails  extends Serializable {
    Collection<? extends GrantedAuthority> getAuthorities();

    String getPassword();
    String getEmail();
    String getUsername();

    boolean isAccountNonExpired();

    boolean isAccountNonLocked();

    boolean isCredentialsNonExpired();

    boolean isEnabled();
}
