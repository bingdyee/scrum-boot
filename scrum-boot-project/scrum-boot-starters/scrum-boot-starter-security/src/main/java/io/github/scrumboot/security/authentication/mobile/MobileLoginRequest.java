package io.github.scrumboot.security.authentication.mobile;

/**
 * @author bingdyee
 * @since 2022/03/02
 */
public class MobileLoginRequest {

    /**
     * 手机号
     */
    private String mobile;

    /**
     * 密码 | 验证码
     */
    private String password;

    /**
     * 登录类型：1-手机号密码；2-验证码
     */
    private Integer type;

    public String getMobile() {
        return mobile;
    }

    public void setMobile(String mobile) {
        this.mobile = mobile;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Integer getType() {
        return type;
    }

    public void setType(Integer type) {
        this.type = type;
    }
}
