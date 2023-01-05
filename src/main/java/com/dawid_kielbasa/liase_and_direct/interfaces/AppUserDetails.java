package com.dawid_kielbasa.liase_and_direct.interfaces;

import org.springframework.security.core.GrantedAuthority;

import java.io.Serializable;
import java.util.Collection;

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
