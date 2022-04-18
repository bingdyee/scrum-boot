package io.github.scrumboot.security.social;


import io.github.scrumboot.langs.http.ExecutableHttpClient;
import io.github.scrumboot.langs.http.IHttpRequest;

/**
 * @author bingdyee
 * @since 2022/03/23
 */
public abstract class SocialAuthenticationProvider {

    private static final ExecutableHttpClient HTTP_CLIENT = ExecutableHttpClient.newInstance();

    protected final String socialType;

    protected final ClientRegistration clientRegistration;

    public SocialAuthenticationProvider(String socialType, ClientRegistration clientRegistration) {
        this.socialType = socialType;
        this.clientRegistration = clientRegistration;
    }

    /**
     * 获取用户信息
     *
     * @param code 授权码
     * @return 用户信息
     */
    public abstract SocialUserInfo getSocialUserInfo(String code);

    public boolean support(String type) {
        return this.socialType.equals(type);
    }

    public <T> T request(String url, IHttpRequest<T> request) {
        return HTTP_CLIENT.execute(url, request);
    }

}
