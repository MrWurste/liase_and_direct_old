package com.dawid_kielbasa.liase_and_direct.controllers;

import com.dawid_kielbasa.liase_and_direct.config.FilterUtils;
import com.dawid_kielbasa.liase_and_direct.dto.AuthenticationRequest;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetails;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetailsService;
import com.dawid_kielbasa.liase_and_direct.models.AppUser;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.web.bind.annotation.*;

import java.util.Collections;

/**
 * Controller class with API endpoints. For now only for authentication and registry user. I didn't have time for more.
 */
@CrossOrigin
@RestController
@RequestMapping("/login")
@RequiredArgsConstructor
public class AuthenticationController {

    private final AuthenticationManager authenticationManager;
    private final AppUserDetailsService userDetailsService;
    private final FilterUtils filterUtils;
    @GetMapping
    public String loginPage() {
        return "login page placeholder";
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        /*authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()));*/ //TODO repair authentication, possible rewrite
        final AppUserDetails email = userDetailsService.loadUserByEmail(authenticationRequest.getEmail());
        System.out.println(userDetailsService.loadUserByEmail(authenticationRequest.getEmail()));
        if (email != null) {
            return ResponseEntity.ok(filterUtils.generateToken(email));
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
    //TODO Prevent to register with the same email
    @PostMapping("/register")
    public ResponseEntity<String> register(@RequestBody AuthenticationRequest registerRequest) {
        System.out.println( registerRequest.getEmail());
        //final AppUserDetails email = userDetailsService.loadUserByEmail(registerRequest.getEmail());

        //if (email != null) {
        //    ResponseEntity.status(400).body("Account already existed");
       // } else {
            final AppUserDetails user = new AppUser(registerRequest.getUsername(), registerRequest.getEmail(), registerRequest.getPassword(), Collections.singleton(new SimpleGrantedAuthority("ROLE_USER")));
        System.out.println(user.getUsername() + " " + user.getEmail() + " " + user.getPassword() + " " + user.getAuthorities());
            userDetailsService.addUser(user);
        //}
        return ResponseEntity.ok("registered");
    }
}
