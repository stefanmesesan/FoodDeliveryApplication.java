package com.example.licenta.security;

import com.example.licenta.exception.ApiException;
import com.example.licenta.utils.JwtUtils;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import org.hibernate.annotations.Filter;
import org.jetbrains.annotations.NotNull;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.util.StringUtils;
import org.springframework.web.filter.OncePerRequestFilter;

import java.io.IOException;

import static com.example.licenta.utils.Constants.AUTHORIZATION;
import static com.example.licenta.utils.Constants.BEARER;

@Filter(name = "Auth")
public class AuthTokenFilter extends OncePerRequestFilter {

    private final JwtUtils jwtUtils;

    public AuthTokenFilter(JwtUtils jwtUtils) {
        this.jwtUtils = jwtUtils;
    }

    @Override
    protected void doFilterInternal(@NotNull HttpServletRequest request, @NotNull HttpServletResponse response, @NotNull FilterChain filterChain) throws ApiException, ServletException, IOException {
        String jwt = extractJwt(request);
        if (jwt != null && jwtUtils.validateToken(jwt,false)) {

            UserDetails userDetails = jwtUtils.extractDetails(jwt);
            SecurityContextHolder.getContext().setAuthentication(map(userDetails));

        }
        filterChain.doFilter(request, response);
    }

    private String extractJwt(HttpServletRequest request) {
        String headerAuth = request.getHeader(AUTHORIZATION);

        if (StringUtils.hasText(headerAuth) && headerAuth.startsWith(BEARER)) {
            String[] jwtSplit = headerAuth.split(" ");
            return jwtSplit[1];
        }
        return null;
    }

    private Authentication map(UserDetails userDetails) {
        return new JwtToken(userDetails);
    }
}
