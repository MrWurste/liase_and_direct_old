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
