package io.github.scrumboot.security.social;

import io.github.scrumboot.langs.exception.AuthenticationFailedException;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.web.authentication.preauth.PreAuthenticatedAuthenticationToken;

import java.util.ArrayList;
import java.util.List;

/**
 * 三方账号登录 - 管理
 *
 * @author bingdyee
 * @since 2022/03/23
 */
public class SocialAuthenticationManager {

    private final SocialUserService socialUserService;

    private final List<SocialAuthenticationProvider> providerList;

    public SocialAuthenticationManager(SocialUserService socialUserService) {
        this.socialUserService = socialUserService;
        this.providerList = new ArrayList<>();
    }

    public Authentication authenticate(SocialRequest socialRequest) {
        SocialAuthenticationProvider authenticationProvider = providerList.stream()
                .filter(provider -> provider.support(socialRequest.getSocialType())).findFirst()
                .orElseThrow(() -> new AuthenticationFailedException("暂不支持该登录方式"));
        SocialUserInfo socialUserInfo = authenticationProvider.getSocialUserInfo(socialRequest.getCode());
        socialUserInfo.setSocialType(socialRequest.getSocialType());
        UserDetails userDetails = socialUserService.loadUser(socialUserInfo);
        return new PreAuthenticatedAuthenticationToken(userDetails, null, new ArrayList<>());
    }

    public void addProvider(SocialAuthenticationProvider provider) {
        this.providerList.add(provider);
    }

}
