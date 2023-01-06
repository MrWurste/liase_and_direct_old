package com.dawid_kielbasa.liase_and_direct.exceptions;

import org.springframework.security.core.AuthenticationException;

/**
 * Just custom exception class. Framework doesn't have Email not found exception.
 */
public class EmailNotFoundException extends AuthenticationException {
    public EmailNotFoundException(String msg) {
        super(msg);
    }
    public EmailNotFoundException(String msg, Throwable cause) {
        super(msg, cause);
    }
}
