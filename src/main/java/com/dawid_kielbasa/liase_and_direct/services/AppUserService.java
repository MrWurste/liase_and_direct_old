package com.dawid_kielbasa.liase_and_direct.services;

import com.dawid_kielbasa.liase_and_direct.repos.AppUserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AppUserService {
    @Autowired
    AppUserRepository userRepository;

    public AppUserService(AppUserRepository userRepository) {
        this.userRepository = userRepository;
    }
}
