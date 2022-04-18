package io.github.scrumboot.security.authentication.mobile;

import org.springframework.security.authentication.AbstractAuthenticationToken;
import org.springframework.security.core.GrantedAuthority;

import java.util.Collection;

/**
 * @author bingdyee
 * @since 2022/03/02
 */
public class MobileAuthenticationToken  extends AbstractAuthenticationToken {

    private final Object principal;

    private final Object credentials;

    private final int authType;

    public MobileAuthenticationToken(Object principal, Object credentials, int authType) {
        super(null);
        this.principal = principal;
        this.credentials = credentials;
        this.authType = authType;
        super.setAuthenticated(false);
    }

    public MobileAuthenticationToken(Object principal, int authType, Collection<? extends GrantedAuthority> authorities) {
        super(authorities);
        this.principal = principal;
        this.credentials = null;
        this.authType = authType;
        super.setAuthenticated(true);
    }

    @Override
    public Object getCredentials() {
        return this.credentials;
    }

    @Override
    public Object getPrincipal() {
        return this.principal;
    }

    public Integer getAuthType() {
        return authType;
    }

}
