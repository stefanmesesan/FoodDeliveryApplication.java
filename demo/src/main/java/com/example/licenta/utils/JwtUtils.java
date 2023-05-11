package com.example.licenta.utils;

import com.example.licenta.exception.ApiException;
import com.example.licenta.exception.ErrorKeys;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.MalformedJwtException;
import io.jsonwebtoken.UnsupportedJwtException;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
;
import java.util.Collections;

import static com.example.licenta.utils.Constants.AUTHORITIES;
import static com.example.licenta.utils.Constants.SECRET;

@Component
public class JwtUtils {
    public boolean validateToken(String authToken, boolean refresh) {
        String token = "JWT";
        HttpStatus status = HttpStatus.BAD_REQUEST;

        if (refresh) {
            token = "REFRESH";
            status = HttpStatus.GONE;
        }

        try {
            Jwts.parser().setSigningKey(SECRET).parseClaimsJws(authToken);
            return true;
        } catch (MalformedJwtException e) {
            throw new ApiException("Invalid" + token + " token", ErrorKeys.INVALID_TOKEN, status);
        } catch (ExpiredJwtException e) {
            throw new ApiException(token + " token is expired", ErrorKeys.EXPIRED_TOKEN, status);
        } catch (UnsupportedJwtException e) {
            throw new ApiException(token + " token is unsupported", ErrorKeys.UNSUPPORTED_TOKEN, status);
        } catch (IllegalArgumentException e) {
            throw new ApiException(token + " claims string is empty", ErrorKeys.INVALID_CLAIMS, status);
        }
    }

    public UserDetails extractDetails(String token) {
        Claims body = Jwts.parser().setSigningKey(SECRET).parseClaimsJws(token).getBody();
        String authorities = body.get(AUTHORITIES, String.class);
        String email = body.getSubject();


        return new UserDetailsImplementation(email, Collections.singleton(new SimpleGrantedAuthority(authorities)));
    }
}
