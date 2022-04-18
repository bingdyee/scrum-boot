package io.github.scrumboot.security.social.wechat.response;

import com.fasterxml.jackson.annotation.JsonProperty;
import io.github.scrumboot.security.social.wechat.WeChatResponse;

/**
 * @author Bing D. Yee
 * @since 2022/03/23
 */
public class GetWeChatTokenResponse extends WeChatResponse {

    /** 接口调用凭证 */
    @JsonProperty("access_token")
    private String accessToken;

    /** access_token 接口调用凭证超时时间，单位（秒） */
    @JsonProperty("expires_in")
    private Integer expiresIn;

    /** 用户刷新 access_token */
    @JsonProperty("refresh_token")
    private String refreshToken;

    /** 授权用户唯一标识 */
    private String openid;

    /** 用户授权的作用域，使用逗号（,）分隔 */
    private String scope;

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public Integer getExpiresIn() {
        return expiresIn;
    }

    public void setExpiresIn(Integer expiresIn) {
        this.expiresIn = expiresIn;
    }

    public String getRefreshToken() {
        return refreshToken;
    }

    public void setRefreshToken(String refreshToken) {
        this.refreshToken = refreshToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getScope() {
        return scope;
    }

    public void setScope(String scope) {
        this.scope = scope;
    }
}
