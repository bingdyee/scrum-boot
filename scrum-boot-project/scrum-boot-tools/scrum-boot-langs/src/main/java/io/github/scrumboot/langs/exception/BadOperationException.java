package io.github.scrumboot.langs.exception;

import io.github.scrumboot.langs.model.status.StatusCode;

/**
 * @author bingdyee
 * @since 2022/03/22
 */
public class BadOperationException extends AbstractWebException {

    public BadOperationException() {
        super(StatusCode.INVALID_REQUEST.getCode(), "不允许的操作");
    }

    public BadOperationException(String message) {
        super(StatusCode.INVALID_REQUEST.getCode(), message);
    }

}
