package com.rechaox.scrum.status;

/**
 * @author Bing D. Yee
 * @since 2021/09/04
 */
public interface StatusInfo {

    /**
     * 状态码
     *
     * @return 状态码
     */
    String getCode();

    /**
     * 状态说明
     *
     * @return 状态说明
     */
    String getDesc();

    /**
     * 状态码复用
     *
     * @param desc 状态说明
     * @return 新状态
     */
    default StatusInfo as(String desc) {
        StatusInfo statusInfo = this;
        return new StatusInfo() {
            @Override
            public String getCode() {
                return statusInfo.getCode();
            }

            @Override
            public String getDesc() {
                return desc;
            }
        };
    }

}
