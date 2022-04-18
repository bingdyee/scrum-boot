package io.github.scrumboot.security.social;


/**
 * @author bingdyee
 * @since 2022/03/22
 */
public class SocialUserInfo {

    private String openid;

    private String unionid;

    /** 昵称 */
    private String nickname;

    /** 头像 */
    private String avatar;

    /** 性别 */
    private Integer gender;

    /** 地区 */
    private String region;

    /** 手机 */
    private String mobile;

    /**
     * 三方账号类型
     */
    private String socialType;

    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public Integer getGender() {
        return gender;
    }

    public void setGender(Integer gender) {
        this.gender = gender;
    }

    public String getRegion() {
        return region;
    }

    public void setRegion(String region) {
        this.region = region;
    }

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getSocialType() {
        return socialType;
    }

    public void setSocialType(String socialType) {
        this.socialType = socialType;
    }
}
