package io.github.scrumboot.security.social.wechat.request;

import io.github.scrumboot.langs.http.IHttpRequest;
import io.github.scrumboot.langs.http.RequestMethod;
import io.github.scrumboot.security.social.wechat.response.GetWeChatTokenResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bingdyee
 * @since 2022/03/23
 */
public class GetWeChatTokenRequest implements IHttpRequest<GetWeChatTokenResponse> {

    private String appid;

    private String secret;

    private String code;

    @Override
    public String getApiUri() {
        return "/sns/oauth2/access_token";
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    public Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<>(8);
        params.put("appid", appid);
        params.put("secret", secret);
        params.put("code", code);
        params.put("grant_type", "authorization_code");
        return params;
    }

    @Override
    public Class<GetWeChatTokenResponse> getResponseClass() {
        return GetWeChatTokenResponse.class;
    }

    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }
}
