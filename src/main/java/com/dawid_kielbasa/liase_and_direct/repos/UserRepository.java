package com.dawid_kielbasa.liase_and_direct.repos;

import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

/**
 * Repository class used with User model from spring security framework.
 */
@Deprecated
@Repository
public class UserRepository {

    private final static List<UserDetails> APPLICATION_USERS = Arrays.asList(
            new User("username1","password1", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new User("username2","password2",Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
    );

    public UserDetails findUserByUsername(String username) {
        return APPLICATION_USERS
                .stream()
                .filter(u -> u.getUsername().equals(username))
                .findFirst()
                .orElseThrow(() -> new UsernameNotFoundException("No user or credentials are incorect"));
    }
}
