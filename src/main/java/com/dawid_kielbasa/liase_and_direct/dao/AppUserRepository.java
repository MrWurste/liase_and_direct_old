package com.dawid_kielbasa.liase_and_direct.dao;

import com.dawid_kielbasa.liase_and_direct.exceptions.EmailNotFoundException;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetails;
import com.dawid_kielbasa.liase_and_direct.models.AppUser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Repository;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;

@Repository
public class AppUserRepository {

    //TODO Replace that by actual database;
    private final static List<AppUserDetails> APP_USERS = Arrays.asList(
            new AppUser("username1", "email1@wurst.pl", "password1", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new AppUser("username2", "email2@wurst.pl", "password2", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
    );
    public AppUserDetails findUserByEmail(String email) {
        System.out.println("finding user");
        System.out.println(APP_USERS.get(0).getEmail());
        return APP_USERS
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new EmailNotFoundException("No user or credentials are incorect"));
    }
}
