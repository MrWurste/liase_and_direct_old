package com.dawid_kielbasa.liase_and_direct.interfaces;

import java.util.List;

/**
 * Making repository interface allowed me to use @Autowired JdbcTemplate argument. In old project I didn't use interface of repository,
 * and still had @Autowired JdbcTemplate. I'm not sure why I need to make this, maybe because I used beans in this project.
 */
public interface AppUserRepositoryInterface {

    void registerUser(AppUserDetails appUserDetails);
    AppUserDetails findUserByEmail(String email);
    public void ChangeUserRole (String email, String role);
    public void DeleteUser (String email);
}
