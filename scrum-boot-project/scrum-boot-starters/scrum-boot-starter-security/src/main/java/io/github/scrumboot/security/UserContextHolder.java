package io.github.scrumboot.security;

import org.springframework.security.authentication.AnonymousAuthenticationToken;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;

/**
 * 用户信息获取
 *
 * @author yubinbin
 * @since 2022/03/01
 */
public class UserContextHolder {

    public static boolean isAuthed() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication != null;
    }

    public static Long getCurrentUserId() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        return authentication == null || authentication.getClass().isAssignableFrom(AnonymousAuthenticationToken.class) ? null : Long.valueOf(authentication.getName());
    }

}
