package com.dawid_kielbasa.liase_and_direct.config;

import javax.servlet.FilterChain;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetails;
import com.dawid_kielbasa.liase_and_direct.interfaces.AppUserDetailsService;
import lombok.RequiredArgsConstructor;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.web.authentication.WebAuthenticationDetailsSource;
import org.springframework.stereotype.Component;
import org.springframework.web.filter.OncePerRequestFilter;


import java.io.IOException;

import static org.springframework.http.HttpHeaders.AUTHORIZATION;

/**
 * This AuthFilter class get credentials (email - unique in database, password) and send them to repository, to check if the user exists in this database.
 * If the user is found filter use FilterUtils class to generate token from user data from database and send token to frontend.
 * If frontend send back token, the access is granted without login. Token has saved expire time. If expired token is sended from frontend, the backend crash.
 * I didn't have time for handling this expired token. Login to app with token works with Postman, when created token is senden as Bearer token in Header.
 */
@Component
@RequiredArgsConstructor
public class AuthFilter extends OncePerRequestFilter {

    private final AppUserDetailsService userDetailsService;
    private final FilterUtils filterUtils;
    @Override
    protected void doFilterInternal(
            HttpServletRequest request,
            HttpServletResponse response,
            FilterChain filterChain) throws ServletException, IOException {
        final String authHeader = request.getHeader(AUTHORIZATION);
        final String username;
        final String email;
        final String token;

        if (authHeader == null || !authHeader.startsWith("Bearer")) {
            filterChain.doFilter(request, response);
            return;
        }
        token = authHeader.substring(7);
        email = filterUtils.extractEmail(token);
        if (email != null && SecurityContextHolder.getContext().getAuthentication() == null) {
            AppUserDetails userDetails = userDetailsService.loadUserByEmail(email);
            if (filterUtils.validateToken(token, userDetails)) {
                UsernamePasswordAuthenticationToken authenticationToken =
                new UsernamePasswordAuthenticationToken(userDetails, null, userDetails.getAuthorities());
                authenticationToken.setDetails(new WebAuthenticationDetailsSource().buildDetails(request));
                SecurityContextHolder.getContext().setAuthentication(authenticationToken);
            }
        }
    }
}
