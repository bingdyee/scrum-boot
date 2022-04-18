package io.github.scrumboot.security.social;

import org.springframework.security.core.userdetails.UserDetails;

/**
 * @author bingdyee
 * @since 2022/03/23
 */
public interface SocialUserService {

    /**
     * 获取用户信息
     *
     * @param socialUser 三方用户信息
     * @return 用户信息
     */
    UserDetails loadUser(SocialUserInfo socialUser);

}
