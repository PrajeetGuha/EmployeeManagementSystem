package org.antwalk.ems.security;

import java.util.List;
import java.util.stream.Collectors;

import org.antwalk.ems.model.User;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

public class AuthenticationSystem {
    
    public static boolean isLoggedAs(String role){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        List<String> roles = authentication.getAuthorities().stream().map(r -> r.getAuthority()).collect(Collectors.toList());
        return roles.contains(role);
    }

    public static Long getId(){
        final Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        LoginUserDetails loginUserDetails = (LoginUserDetails)authentication.getPrincipal();
        return loginUserDetails.getTablePk();  
    }
}
