package io.github.scrumboot.security.social.wechat;


/**
 * @author bingdyee
 * @since 2022/03/24
 */
public class WeChatResponse {

    private Integer errcode;

    private String errmsg;

    public boolean success() {
        return errcode == null;
    }

    public Integer getErrcode() {
        return errcode;
    }

    public void setErrcode(Integer errcode) {
        this.errcode = errcode;
    }

    public String getErrmsg() {
        return errmsg;
    }

    public void setErrmsg(String errmsg) {
        this.errmsg = errmsg;
    }
}
