package io.github.scrumboot.security.authentication.mobile;

import io.github.scrumboot.security.AuthenticationChecker;
import io.github.scrumboot.security.IUserDetailsService;
import io.github.scrumboot.security.properties.SecurityConstant;
import org.springframework.security.authentication.AuthenticationProvider;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.AuthenticationException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.ArrayList;

/**
 * @author bingdyee
 * @since 2022/03/02
 */
public class MobileAuthenticationProvider implements AuthenticationProvider {

    private final PasswordEncoder passwordEncoder;

    private final IUserDetailsService userDetailService;

    private final AuthenticationChecker authenticationChecker;

    public MobileAuthenticationProvider(IUserDetailsService userDetailService, AuthenticationChecker authenticationChecker, PasswordEncoder passwordEncoder) {
        this.userDetailService = userDetailService;
        this.authenticationChecker = authenticationChecker;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public Authentication authenticate(Authentication authentication) throws AuthenticationException {
        MobileAuthenticationToken authenticationToken = (MobileAuthenticationToken) authentication;
        if (SecurityConstant.SMS_LOGIN == authenticationToken.getAuthType()) {
            authenticationChecker.check(authenticationToken);
        }
        String mobile = (String) authentication.getPrincipal();
        UserDetails userDetails = userDetailService.loadUserByMobile(mobile, authenticationToken.getAuthType());
        String presentedPassword = authentication.getCredentials().toString();
        if (SecurityConstant.PWD_LOGIN == authenticationToken.getAuthType() &&
                !this.passwordEncoder.matches(presentedPassword, userDetails.getPassword())) {
                throw new BadCredentialsException("手机号或密码错误");
        }
        return new MobileAuthenticationToken(userDetails, authenticationToken.getAuthType(), new ArrayList<>());
    }

    @Override
    public boolean supports(Class<?> authentication) {
        return MobileAuthenticationToken.class.isAssignableFrom(authentication);
    }

}