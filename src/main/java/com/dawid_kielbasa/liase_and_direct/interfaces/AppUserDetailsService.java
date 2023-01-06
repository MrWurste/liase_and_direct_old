package com.dawid_kielbasa.liase_and_direct.interfaces;

import com.dawid_kielbasa.liase_and_direct.exceptions.EmailNotFoundException;

/**
 * Interface used to make Bean with repository methods.
 */
public interface AppUserDetailsService {
    AppUserDetails loadUserByEmail(String email) throws EmailNotFoundException;

    void addUser(AppUserDetails appUserDetails) throws EmailNotFoundException;
}
