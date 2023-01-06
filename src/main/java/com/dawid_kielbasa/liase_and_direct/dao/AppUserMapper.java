package com.dawid_kielbasa.liase_and_direct.dao;

import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetails;
import com.dawid_kielbasa.liase_and_direct.models.AppUser;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Collection;
import java.util.Collections;

/**
 * Class that is used to map results from database.
 */
public class AppUserMapper implements RowMapper<AppUserDetails> {

    @Override
public AppUser mapRow(ResultSet rs, int rowNum) throws SQLException {
        AppUser appUser = new AppUser(
                rs.getString("username"),
                rs.getString("email"),
                rs.getString("password"),
                //(Collection<? extends GrantedAuthority>) rs.getString("authorities")
                Collections.singleton(new SimpleGrantedAuthority(rs.getString("authorities")))
        );
        return appUser;
    }
}
