package io.github.scrumboot.security;

import org.springframework.security.authentication.AbstractAuthenticationToken;

/**
 * @author bingdyee
 * @since 2022/03/02
 */
public interface AuthenticationChecker {

    /**
     * 校验登录参数
     *
     * @param authentication T extends AbstractAuthenticationToken
     */
    void check(AbstractAuthenticationToken authentication);

}