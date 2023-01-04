package com.dawid_kielbasa.liase_and_direct.controllers;

import com.dawid_kielbasa.liase_and_direct.config.FilterUtils;
import com.dawid_kielbasa.liase_and_direct.dto.AuthenticationRequest;
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
    private final UserDetailsService userDetailsService;
    private final FilterUtils filterUtils;
    @GetMapping
    public String loginPage() {
        return "login page placeholder";
    }
    @PostMapping("/authenticate")
    public ResponseEntity<String> authenticate(@RequestBody AuthenticationRequest authenticationRequest) {
        System.out.println("Frontend tu był");
        authenticationManager.authenticate(new UsernamePasswordAuthenticationToken(
                authenticationRequest.getEmail(),
                authenticationRequest.getPassword()));
        final UserDetails user = userDetailsService.loadUserByUsername(authenticationRequest.getEmail());
        if (user != null) {
            System.out.println("Coś się zalogowało");
            return ResponseEntity.ok(filterUtils.generateToken(user));
        } else {
            return ResponseEntity.status(404).body("User not found");
        }
    }
}
