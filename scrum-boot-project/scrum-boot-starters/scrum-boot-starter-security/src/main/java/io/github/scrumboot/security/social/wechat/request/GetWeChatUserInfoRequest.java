package io.github.scrumboot.security.social.wechat.request;

import io.github.scrumboot.langs.http.IHttpRequest;
import io.github.scrumboot.langs.http.RequestMethod;
import io.github.scrumboot.security.social.wechat.response.GetWeChatUserInfoResponse;

import java.util.HashMap;
import java.util.Map;

/**
 * @author bingdyee
 * @since 2022/03/24
 */
public class GetWeChatUserInfoRequest implements IHttpRequest<GetWeChatUserInfoResponse> {

    private String accessToken;

    private String openid;

    private String lang = "zh_CN";

    @Override
    public String getApiUri() {
        return "/sns/userinfo";
    }

    @Override
    public RequestMethod getRequestMethod() {
        return RequestMethod.GET;
    }

    @Override
    public Map<String, String> getRequestParams() {
        Map<String, String> params = new HashMap<>(8);
        params.put("access_token", accessToken);
        params.put("openid", openid);
        params.put("lang", lang);
        return params;
    }

    @Override
    public Class<GetWeChatUserInfoResponse> getResponseClass() {
        return GetWeChatUserInfoResponse.class;
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getLang() {
        return lang;
    }

    public void setLang(String lang) {
        this.lang = lang;
    }
}
