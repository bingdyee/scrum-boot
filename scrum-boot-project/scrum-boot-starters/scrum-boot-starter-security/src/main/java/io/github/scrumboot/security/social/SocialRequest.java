package io.github.scrumboot.security.social;

/**
 * @author bingdyee
 * @since 2022/03/23
 */
public class SocialRequest {

    private final String socialType;

    private final String code;

    private SocialUserInfo userInfo;

    public SocialRequest(String socialType, String code) {
        this.socialType = socialType;
        this.code = code;
    }

    public void setUserInfo(SocialUserInfo userInfo) {
        this.userInfo = userInfo;
    }

    public String getSocialType() {
        return socialType;
    }

    public String getCode() {
        return code;
    }

    public SocialUserInfo getUserInfo() {
        return userInfo;
    }
}
