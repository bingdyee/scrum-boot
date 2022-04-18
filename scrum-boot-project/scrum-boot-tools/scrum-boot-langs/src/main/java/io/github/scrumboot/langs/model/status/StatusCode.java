package io.github.scrumboot.langs.model.status;

/**
 * 默认状态码（5位）
 *      10** 系统默认状态码
 *      11** 业务系统错误码
 *
 * @author Bing D. Yee
 * @since 2021/09/05
 */
public enum StatusCode implements StatusInfo {
    /** 正确返回 */
    OK(0, "success"),
    INVALID_REQUEST(10400, "请求参数错误"),
    UN_AUTH(10401, "请先登录"),
    BAD_REFRESH_TOKEN(10402, "刷新令牌已过期"),
    FORBIDDEN(10403, "权限不足"),
    NOT_FOUND(10404, "请求资源不存在"),
    LOGIN_FAILED(10405, "登录失败"),
    BUSINESS_ERROR(10500, "业务执行出错"),
    INTERNAL_SERVER_ERROR(20500, "服务器繁忙"),
    UN_SUPPORT_ENUM_CODE(20500, "不支持的枚举编码"),
    SERVICE_UNAVAILABLE(30500, "调用第三方服务出错");


    private final int code;
    private final String desc;

    StatusCode(int code, String desc) {
        this.code = code;
        this.desc = desc;
    }

    @Override
    public int getCode() {
        return this.code;
    }

    @Override
    public String getDesc() {
        return this.desc;
    }

}
