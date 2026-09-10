package com.portifolio.marketAPI.userPrincipal;

import com.portifolio.marketAPI.entity.User;
import com.portifolio.marketAPI.entity.enums.UserRole;
import com.portifolio.marketAPI.exception.ForbiddenException;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

public class UserPrincipal implements UserDetails {

    private final String id;

    private final String name;

    private final String password;

    private final UserRole userRole;

    private final String establishmentId;

    public UserPrincipal(User user) {
        this.id = user.getId();
        this.name = user.getName();
        this.password = user.getPassword();
        this.userRole = user.getUserRole();
        this.establishmentId = user.getEstablishment().getId();
    }

    public Collection<? extends GrantedAuthority> getAuthorities() {
        List<SimpleGrantedAuthority> authorities;
        switch (userRole){
            case ADMIN ->
                authorities = List.of(
                        new SimpleGrantedAuthority("ADMIN"),
                        new SimpleGrantedAuthority("OWNER"),
                        new SimpleGrantedAuthority("MANAGER"),
                        new SimpleGrantedAuthority("EMPLOYEE"));

            case OWNER ->
                authorities = List.of(
                        new SimpleGrantedAuthority("OWNER"),
                        new SimpleGrantedAuthority("MANAGER"),
                        new SimpleGrantedAuthority("EMPLOYEE"));

            case MANAGER ->
                authorities = List.of(
                        new SimpleGrantedAuthority("MANAGER"),
                        new SimpleGrantedAuthority("EMPLOYEE"));

            case EMPLOYEE ->
                authorities = List.of( new SimpleGrantedAuthority("EMPLOYEE"));

            case INACTIVE -> throw new ForbiddenException("autorização revogada ou invalida");
            default -> throw new IllegalStateException("Unexpected value: " + userRole);
        }
        return authorities;
    }

    public String getPassword() {
        return password;
    }

    public String getUsername() {
        return "";
    }

    public String getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public UserRole getUserRole() {
        return userRole;
    }

    public String getEstablishmentId() {
        return establishmentId;
    }
}
