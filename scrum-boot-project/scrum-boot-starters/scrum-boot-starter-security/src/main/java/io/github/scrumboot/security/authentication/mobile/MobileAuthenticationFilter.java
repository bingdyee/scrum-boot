package io.github.scrumboot.security.authentication.mobile;

import io.github.scrumboot.langs.Jsons;
import io.github.scrumboot.langs.Strings;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationServiceException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.security.web.authentication.AbstractAuthenticationProcessingFilter;
import org.springframework.security.web.util.matcher.AntPathRequestMatcher;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;

/**
 * @author bingdyee
 * @since 2022/03/02
 */
public class MobileAuthenticationFilter extends AbstractAuthenticationProcessingFilter {

    protected MobileAuthenticationFilter() {
        super(new AntPathRequestMatcher("/v1/auth/login", "POST"));
    }

    @Override
    public Authentication attemptAuthentication(HttpServletRequest request, HttpServletResponse response) throws AuthenticationException, IOException {
        if (!MediaType.APPLICATION_JSON_VALUE.equals(request.getContentType()) &&
                !request.getContentType().startsWith(MediaType.APPLICATION_JSON_VALUE)) {
            throw new AuthenticationServiceException("Authentication method not supported: " + request.getMethod());
        }
        MobileLoginRequest params = Jsons.toObject(request.getInputStream(), MobileLoginRequest.class);
        if (Strings.isBlank(params.getMobile()) || Strings.isBlank(params.getPassword()) || params.getType() == null) {
            throw new UsernameNotFoundException("登录失败");
        }
        MobileAuthenticationToken authRequest = new MobileAuthenticationToken(params.getMobile(), params.getPassword(), params.getType());
        this.setDetails(request, authRequest);
        return this.getAuthenticationManager().authenticate(authRequest);
    }

    protected void setDetails(HttpServletRequest request, MobileAuthenticationToken authRequest) {
        authRequest.setDetails(this.authenticationDetailsSource.buildDetails(request));
    }

}
