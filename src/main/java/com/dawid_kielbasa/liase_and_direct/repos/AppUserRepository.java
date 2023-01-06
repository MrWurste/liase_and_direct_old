package com.dawid_kielbasa.liase_and_direct.repos;

import com.dawid_kielbasa.liase_and_direct.dao.AppUserMapper;
import com.dawid_kielbasa.liase_and_direct.exceptions.EmailNotFoundException;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetails;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserRepositoryInterface;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.util.List;

/**
 * Repository class that send requests to database and receave data. I have a little trouble with @Autowired JdbcTemplate (jdbcTemplate moght not be initialized),
 * but this code works...
 */
@Repository
public class AppUserRepository implements AppUserRepositoryInterface {

    @Autowired
    JdbcTemplate jdbcTemplate;

    public List<AppUserDetails> APP_USERS;

    public List<AppUserDetails> getUsersList() {
       APP_USERS = jdbcTemplate.query("SELECT * FROM Users", new AppUserMapper());
       return APP_USERS;
    }

    /**Fake DataBase for testing*/
    /*private final static List<AppUserDetails> APP_USERS = Arrays.asList(
            new AppUser("username1", "email1@wurst.pl", "password1", Collections.singleton(new SimpleGrantedAuthority("ROLE_ADMIN"))),
            new AppUser("username2", "email2@wurst.pl", "password2", Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")))
    );*/

    /**
     * Method to add new user to database. Authorities should be with only one value until handling with more will be made.
     * Authorities are translated into proper String value (s) using regex. Square brackets are removed.
     * @param appUserDetails AppUser values (username, email, password, authorities)
     */
    @Override
    public void registerUser(AppUserDetails appUserDetails) {
        //if (findUserByEmail(appUserDetails.getEmail()) == null) {
        System.out.println("UserRepo.registerUser " +appUserDetails.getAuthorities());
        String s = appUserDetails.getAuthorities().toString().replaceAll("[\\[\\]]","");
            jdbcTemplate.update("INSERT INTO Users (username,email,password,authorities) VALUES (?,?,?,?)",
                    appUserDetails.getUsername(),
                    appUserDetails.getEmail(),
                    appUserDetails.getPassword(),
                    s);
        //}
    }

    /**
     * Method that check if user with given email is in database.
     * @param email String value used to find user in database
     * @return
     */
    @Override
    public AppUserDetails findUserByEmail(String email) {
        getUsersList();
        return APP_USERS
                .stream()
                .filter(u -> u.getEmail().equals(email))
                .findFirst()
                .orElseThrow(() -> new EmailNotFoundException("No user or credentials are incorect"));
    }

    /**
     * Code to change user role. Admin user from frontend should activate this code on his userlist, but I failed to implement it.
     * @param email parameter that is used to find user in database. In database marked as unique.
     * @param role new role for user. ROLES: ROLE_USER, ROLE_ADMIN. Other values will not work.
     */
    @Override
    public void ChangeUserRole (String email, String role) {
        jdbcTemplate.update("Update Users Set authorities="+role+"where email="+email);
    }

    /**
     * Method to delete user from database. Another functionality for admin user.
     * @param email Unique value to find user in database
     */
    @Override
    public void DeleteUser (String email) {
        jdbcTemplate.execute("Delete from users where email="+email);
    }
}
