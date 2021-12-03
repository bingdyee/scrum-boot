package com.rechaox.scrum.http;

/**
 * @author Bing D. Yee
 * @since 2021/10/08
 */
public class ServiceInvokeException extends RuntimeException {

    public ServiceInvokeException() {
        super("调用第三方接口失败");
    }

    public ServiceInvokeException(String message) {
        super("调用第三方接口失败：" + message);
    }

    public ServiceInvokeException(String message, Throwable cause) {
        super(message, cause);
    }

    public ServiceInvokeException(Throwable cause) {
        super(cause);
    }

    protected ServiceInvokeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }

}
