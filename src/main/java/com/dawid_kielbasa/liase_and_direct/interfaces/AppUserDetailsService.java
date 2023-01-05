package com.dawid_kielbasa.liase_and_direct.interfaces;

import com.dawid_kielbasa.liase_and_direct.exceptions.EmailNotFoundException;

public interface AppUserDetailsService {
    AppUserDetails loadUserByEmail(String email) throws EmailNotFoundException;
}
