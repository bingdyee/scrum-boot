package io.github.scrumboot.security.social.wechat;

import io.github.scrumboot.langs.exception.AuthenticationFailedException;
import io.github.scrumboot.security.social.ClientRegistration;
import io.github.scrumboot.security.social.SocialAuthenticationProvider;
import io.github.scrumboot.security.social.SocialUserInfo;
import io.github.scrumboot.security.social.wechat.request.GetWeChatTokenRequest;
import io.github.scrumboot.security.social.wechat.request.GetWeChatUserInfoRequest;
import io.github.scrumboot.security.social.wechat.response.GetWeChatTokenResponse;
import io.github.scrumboot.security.social.wechat.response.GetWeChatUserInfoResponse;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * @author bingdyee
 * @since 2022/03/23
 */
public class WeChatAuthProvider extends SocialAuthenticationProvider {

    private final Logger log = LoggerFactory.getLogger(WeChatAuthProvider.class);

    public WeChatAuthProvider(ClientRegistration clientRegistration) {
        super("wechat", clientRegistration);
    }

    @Override
    public SocialUserInfo getSocialUserInfo(String code) {
        GetWeChatTokenResponse response = getWeChatToken(code);
        GetWeChatUserInfoResponse userInfoResponse = getWeCahtUserInfo(response.getAccessToken(), response.getOpenid());
        SocialUserInfo userInfo = new SocialUserInfo();
        userInfo.setOpenid(userInfoResponse.getOpenid());
        userInfo.setUnionid(userInfoResponse.getUnionid());
        userInfo.setAvatar(userInfoResponse.getHeadimgurl());
        userInfo.setGender(userInfoResponse.getSex());
        userInfo.setNickname(userInfoResponse.getNickname());
        return userInfo;
    }

    /**
     * 通过 code 获取 access_token
     *
     * @param code 授权码
     * @return access_token
     */
    private GetWeChatTokenResponse getWeChatToken(String code) {
        GetWeChatTokenRequest getWeChatTokenRequest = new GetWeChatTokenRequest();
        getWeChatTokenRequest.setAppid(clientRegistration.getAppKey());
        getWeChatTokenRequest.setSecret(clientRegistration.getAppSecret());
        getWeChatTokenRequest.setCode(code);
        GetWeChatTokenResponse response = request(this.clientRegistration.getServerUrl(), getWeChatTokenRequest);
        if (!response.success()) {
            log.error("微信登录获取 Token 失败：{}-{}", response.getErrcode(), response.getErrmsg());
            throw new AuthenticationFailedException("授权码错误");
        }
        return response;
    }

    /**
     * 获取用户个人信息（UnionID 机制）
     *
     * @param accessToken 调用凭证
     * @param openid 普通用户的标识，对当前开发者帐号唯一
     * @return 微信用户信息
     */
    private GetWeChatUserInfoResponse getWeCahtUserInfo(String accessToken, String openid) {
        GetWeChatUserInfoRequest getWeChatUserInfoRequest = new GetWeChatUserInfoRequest();
        getWeChatUserInfoRequest.setAccessToken(accessToken);
        getWeChatUserInfoRequest.setOpenid(openid);
        GetWeChatUserInfoResponse response = request(this.clientRegistration.getServerUrl(), getWeChatUserInfoRequest);
        if (!response.success()) {
            log.error("微信登录获取”获取用户个人信息“失败：{}-{}", response.getErrcode(), response.getErrmsg());
            throw new AuthenticationFailedException();
        }
        return response;
    }

}
