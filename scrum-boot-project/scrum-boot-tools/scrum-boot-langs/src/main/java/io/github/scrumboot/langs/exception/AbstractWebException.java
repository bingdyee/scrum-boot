package io.github.scrumboot.langs.exception;

import io.github.scrumboot.langs.model.status.StatusCode;
import io.github.scrumboot.langs.model.status.StatusInfo;

/**
 * Web接口请求异常基础类
 *
 * @author Bing D. Yee
 * @since 2021/04/07
 */
public abstract class AbstractWebException extends RuntimeException {

    private static final long serialVersionUID = -6228610265769822498L;

    protected int errorCode;

    public AbstractWebException(String message) {
        this(StatusCode.INTERNAL_SERVER_ERROR.getCode(), message);
    }

    public AbstractWebException(StatusInfo errorStatus) {
        this(errorStatus.getCode(), errorStatus.getDesc());
    }

    public AbstractWebException(int errorCode, String errorDesc) {
        super(errorDesc);
        this.errorCode = errorCode;
    }

    public int getErrorCode() {
        return errorCode;
    }

}