package io.github.scrumboot.security.social.wechat.response;

import io.github.scrumboot.security.social.wechat.WeChatResponse;

import java.util.List;

/**
 * @author bingdyee
 * @since 2022/03/24
 */
public class GetWeChatUserInfoResponse extends WeChatResponse {

    /** 普通用户的标识，对当前开发者帐号唯一 */
    private String openid;

    /** 普通用户昵称 */
    private String nickname;

    /** 普通用户性别，1 为男性，2 为女性 */
    private Integer sex;

    /** 普通用户个人资料填写的省份 */
    private String province;

    /** 普通用户个人资料填写的城市 */
    private String city;

    /** 国家，如中国为 CN */
    private String country;

    /** 用户头像，最后一个数值代表正方形头像大小（有 0、46、64、96、132 数值可选，0 代表 640*640 正方形头像），用户没有头像时该项为空 */
    private String headimgurl;

    /** 用户特权信息，json 数组，如微信沃卡用户为（chinaunicom） */
    private List<String> privilege;

    /** 用户统一标识。针对一个微信开放平台帐号下的应用，同一用户的 unionid 是唯一的。 */
    private String unionid;


    public String getOpenid() {
        return openid;
    }

    public void setOpenid(String openid) {
        this.openid = openid;
    }

    public String getNickname() {
        return nickname;
    }

    public void setNickname(String nickname) {
        this.nickname = nickname;
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getProvince() {
        return province;
    }

    public void setProvince(String province) {
        this.province = province;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

    public String getCountry() {
        return country;
    }

    public void setCountry(String country) {
        this.country = country;
    }

    public String getHeadimgurl() {
        return headimgurl;
    }

    public void setHeadimgurl(String headimgurl) {
        this.headimgurl = headimgurl;
    }

    public List<String> getPrivilege() {
        return privilege;
    }

    public void setPrivilege(List<String> privilege) {
        this.privilege = privilege;
    }

    public String getUnionid() {
        return unionid;
    }

    public void setUnionid(String unionid) {
        this.unionid = unionid;
    }
}
