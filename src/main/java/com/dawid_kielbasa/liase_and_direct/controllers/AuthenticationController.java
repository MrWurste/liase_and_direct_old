package com.dawid_kielbasa.liase_and_direct.controllers;

import com.dawid_kielbasa.liase_and_direct.config.FilterUtils;
import com.dawid_kielbasa.liase_and_direct.dto.AuthenticationRequest;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetails;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.web.bind.annotation.*;

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
}
