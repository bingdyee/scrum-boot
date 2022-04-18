package io.github.scrumboot.langs.exception;

import io.github.scrumboot.langs.model.status.StatusCode;

/**
 * @author bingdyee
 * @since 2022/03/17
 */
public class InvalidParamException extends AbstractWebException {

    public InvalidParamException() {
        super(StatusCode.INVALID_REQUEST);
    }

    public InvalidParamException(String message) {
        super(StatusCode.INVALID_REQUEST.getCode(), message);
    }


}
