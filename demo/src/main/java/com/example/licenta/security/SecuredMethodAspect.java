package com.example.licenta.security;

import com.example.licenta.exception.ApiException;
import com.example.licenta.exception.ErrorKeys;
import org.aspectj.lang.ProceedingJoinPoint;
import org.aspectj.lang.annotation.Around;
import org.aspectj.lang.annotation.Aspect;
import org.aspectj.lang.annotation.Pointcut;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Component;

import java.util.Arrays;
import java.util.Collection;

import static com.example.licenta.utils.Constants.NOT_ENOUGH_AUTHORITIES;

@Aspect
@Component
public class SecuredMethodAspect {
    @Pointcut("@annotation(secured)")
    public void callAt(Secured secured) {
    }
    @Around(value = "callAt(secured)", argNames = "pjp,secured")
    public Object around(ProceedingJoinPoint pjp,
                         Secured secured) throws Throwable {
        Collection<? extends GrantedAuthority> userAuthorities = SecurityContextHolder.getContext().getAuthentication().getAuthorities();

        if (Arrays.toString(secured.role())
                .replace("[", "")
                .replace("]", "")
                .contains(userAuthorities.toString()
                        .replace("[", "")
                        .replace("]", "")))

            return pjp.proceed();
        else throw new ApiException(NOT_ENOUGH_AUTHORITIES, ErrorKeys.UNAUTHORIZED, HttpStatus.UNAUTHORIZED);
    }
}
