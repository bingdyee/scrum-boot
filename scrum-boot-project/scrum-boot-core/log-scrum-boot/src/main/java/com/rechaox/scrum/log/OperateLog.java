package com.rechaox.scrum.log;

import lombok.Builder;
import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

import java.time.LocalDateTime;

/**
 * 操作日志表：sys_operate_log
 *
 * @author Bing D. Yee
 * @since 2021/09/25
 */
@Getter
@Setter
@Builder
@ToString
public class OperateLog {

    private Long id;

    /** 模块 */
    private String module;

    /** 功能 */
    private String action;

    /** 日志内容 */
    private String context;

    /** 请求路径 */
    private String requestUrl;

    /** 请求IP */
    private String remoteAddr;

    /** 请求参数 */
    private String requestParams;

    /** 请求是否成功 */
    private String succeed;

    /** 请求失败错误信息 */
    private String errorMessage;

    /** 操作人ID */
    private Long operateBy;

    /** 响应时间(RT) ms */
    private Long responseTime;

    /** 创建时间 */
    private LocalDateTime createTime;

}
