package io.github.scrumboot.security;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.scrumboot.langs.constant.Constant;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.Collection;
import java.util.List;

/**
 *
 *
 * @author yubinbin
 * @since 2022/03/01
 */
public class UserPrincipal implements UserDetails {

    private static final long serialVersionUID = -5005705524613649632L;

    private Long userId;
    private String password;
    private Integer status;
    private Collection<SimpleGrantedAuthority> authorities;

    public UserPrincipal() {}

    public UserPrincipal(Long userId, String password, Integer status) {
        this.userId = userId;
        this.password = password;
        this.status = status;
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_APP"));
    }

    public UserPrincipal(Long userId, Integer status) {
        this.userId = userId;
        this.status = status;
        this.authorities = List.of(new SimpleGrantedAuthority("ROLE_APP"));
    }

    public UserPrincipal(Long userId, String password, Integer status, Collection<SimpleGrantedAuthority> authorities) {
        this.userId = userId;
        this.password = password;
        this.status = status;
        this.authorities = authorities;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {
        return authorities;
    }

    @Override
    public String getPassword() {
        return password;
    }

    @Override
    public String getUsername() {
        return userId.toString();
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isAccountNonLocked() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isCredentialsNonExpired() {
        return true;
    }

    @JsonIgnore
    @Override
    public boolean isEnabled() {
        return status.equals(Constant.YES);
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public void setAuthorities(Collection<SimpleGrantedAuthority> authorities) {
        this.authorities = authorities;
    }

    public Integer getStatus() {
        return status;
    }

    @Override
    public String toString() {
        return "UserPrincipal{" +
                ", username='" + userId + '\'' +
                ", password='" + password + '\'' +
                ", status=" + status +
                ", authorities=" + authorities +
                '}';
    }
}
