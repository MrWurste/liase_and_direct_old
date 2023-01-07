package com.dawid_kielbasa.liase_and_direct.dto;

import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

/**
 * Model of authentication request. String value username is not needed, but deadline is too close. No time to repairs.
 * It shouldn't cause any  problems, but this project was a one BIG EXCEPTION...
 */
@Getter
@Setter
@NoArgsConstructor
public class AuthenticationRequest {
    private String username; //Todo Remove this argument, not needed
    private String email;
    private String password;
}
