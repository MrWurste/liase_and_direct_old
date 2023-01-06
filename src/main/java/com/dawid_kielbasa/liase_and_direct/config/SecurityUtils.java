package com.dawid_kielbasa.liase_and_direct.config;

import com.dawid_kielbasa.liase_and_direct.exceptions.EmailNotFoundException;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetails;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetailsService;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserRepositoryInterface;
import lombok.RequiredArgsConstructor;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.config.annotation.authentication.configuration.AuthenticationConfiguration;
import org.springframework.security.crypto.password.NoOpPasswordEncoder;
import org.springframework.security.crypto.password.PasswordEncoder;

/**
 * Utility class used by SecutityConfig class. Here are created Beans of interfaces, which are injected in other classes.
 */
@Configuration
@RequiredArgsConstructor
public class SecurityUtils {
    private final AppUserRepositoryInterface userRepository;
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

            @Override
            public void addUser(AppUserDetails appUserDetails) throws EmailNotFoundException {
                System.out.println("SecurityUtils.addUser");
                userRepository.registerUser(appUserDetails);
            }
        };
    }
}
