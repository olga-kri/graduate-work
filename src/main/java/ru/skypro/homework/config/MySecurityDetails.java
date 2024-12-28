package ru.skypro.homework.config;

import org.springframework.context.annotation.ScopedProxyMode;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Component;
import org.springframework.web.context.annotation.RequestScope;
import ru.skypro.homework.dto.SecurityDto;

import java.util.Collection;
import java.util.Collections;
import java.util.List;
import java.util.Optional;

@Component
@RequestScope(proxyMode = ScopedProxyMode.TARGET_CLASS)
public class MySecurityDetails implements UserDetails {

    private  SecurityDto securityDto;

    public void setUserDto(SecurityDto userDto) {
        this.securityDto = userDto;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
      return   Optional.ofNullable(securityDto)
                .map(SecurityDto::getRole)
                .map(role -> "ROLE_" + role)
                .map(SimpleGrantedAuthority::new)
                .map(Collections::singleton)
                .orElseGet(Collections::emptySet);
    }

    @Override
    public String getPassword() {
        return Optional.ofNullable(securityDto)
                .map(SecurityDto::getPassword)
                .orElse(null);
    }

    @Override
    public String getUsername() {
        return Optional.ofNullable(securityDto)
                .map(SecurityDto::getEmail)
                .orElse(null);
    }

    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @Override
    public boolean isEnabled() {
        return true;
    }
}
