package io.github.scrumboot.langs.exception;

import io.github.scrumboot.langs.model.status.StatusCode;

/**
 * 权限不足
 *
 * @author bingdyee
 * @since 2022/03/16
 */
public class ForbiddenAccessException extends AbstractWebException {

    public ForbiddenAccessException() {
        super(StatusCode.FORBIDDEN);
    }

    public ForbiddenAccessException(String message) {
        super(StatusCode.FORBIDDEN.getCode(), message);
    }

}
