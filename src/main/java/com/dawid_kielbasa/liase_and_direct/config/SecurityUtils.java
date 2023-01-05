package com.dawid_kielbasa.liase_and_direct.config;

import com.dawid_kielbasa.liase_and_direct.dao.AppUserRepository;
import com.dawid_kielbasa.liase_and_direct.dao.UserRepository;
import com.dawid_kielbasa.liase_and_direct.exceptions.EmailNotFoundException;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetails;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.dao.DaoAuthenticationProvider;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

@Configuration
@RequiredArgsConstructor
public class SecurityUtils {
    private final AppUserRepository userRepository;
    /*@Bean
    public AuthenticationProvider authenticationProvider() {
        final DaoAuthenticationProvider authenticationProvider = new DaoAuthenticationProvider();
        authenticationProvider.setUserDetailsService(userDetailsService());
        authenticationProvider.setPasswordEncoder(passwordEncoder());
        return authenticationProvider;
    }*/
    @Bean
    public AuthenticationManager authenticationManager(AuthenticationConfiguration config) throws Exception {
        return config.getAuthenticationManager();
    }
    @Bean
    public PasswordEncoder passwordEncoder() {
        return NoOpPasswordEncoder.getInstance(); //TODO Implement a password encoder
    }
    @Bean
    public AppUserDetailsService userDetailsService() {
        return new AppUserDetailsService() {
            @Override
            public AppUserDetails loadUserByEmail(String email) throws EmailNotFoundException {
                return userRepository.findUserByEmail(email);
            }
        };
    }
}
