package io.github.scrumboot.security;

import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;

/**
 * @author bingdyee
 * @since 2022/03/10
 */
public interface IUserDetailsService extends UserDetailsService {

    /**
     * 手机号登录
     *
     * @param mobile 手机号
     * @param authType 授权类型
     * @return 用户信息
     */
    UserDetails loadUserByMobile(String mobile, int authType);

    /**
     * 用户名查账号
     *
     * @param username 用户名
     * @return 账号
     */
    @Deprecated
    @Override
    default UserDetails loadUserByUsername(String username) { throw new UsernameNotFoundException("不支持用户名登录"); }

}
