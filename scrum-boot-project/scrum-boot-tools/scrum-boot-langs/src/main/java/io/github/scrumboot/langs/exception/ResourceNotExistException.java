package io.github.scrumboot.langs.exception;

import io.github.scrumboot.langs.model.status.StatusCode;

/**
 * @author bingdyee
 * @since 2022/03/09
 */
public class ResourceNotExistException extends AbstractWebException {

    public ResourceNotExistException() {
        super(StatusCode.NOT_FOUND);
    }

    public ResourceNotExistException(String message) {
        super(StatusCode.NOT_FOUND.getCode(), message);
    }

}
