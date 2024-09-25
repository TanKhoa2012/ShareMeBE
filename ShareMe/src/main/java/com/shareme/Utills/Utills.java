package com.shareme.Utills;

import com.shareme.constant.UserRole;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.context.SecurityContextHolder;

import java.util.Collection;

public class Utills {

    public static String getAuthorize(Authentication authentication) {
        Collection<? extends GrantedAuthority> authorities = authentication.getAuthorities();
        if(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_admin")))
            return UserRole.ADMIN;
        else if(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_owner")))
            return UserRole.OWNER;
        else if(authorities.stream().anyMatch(a -> a.getAuthority().equals("ROLE_employee")))
            return UserRole.EMPLOYEE;
        else return UserRole.USER;
    }
}
