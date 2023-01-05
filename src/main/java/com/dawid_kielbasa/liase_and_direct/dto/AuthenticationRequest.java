package com.dawid_kielbasa.liase_and_direct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {
    private String username;
    private String email;
    private String password;
}
