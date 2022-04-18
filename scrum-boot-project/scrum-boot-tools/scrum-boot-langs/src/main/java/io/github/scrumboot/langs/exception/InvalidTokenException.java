package io.github.scrumboot.langs.exception;


import io.github.scrumboot.langs.model.status.StatusCode;
import io.github.scrumboot.langs.model.status.StatusInfo;

/**
 * token
 *
 * @author yubinbin
 * @since 2022/02/28
 */
public class InvalidTokenException extends AbstractWebException {

    private static final long serialVersionUID = 3606212387746368029L;

    public InvalidTokenException(String message) {
        this(StatusCode.UN_AUTH.getCode(), message);
    }

    public InvalidTokenException(StatusInfo errorStatus) {
        super(errorStatus);
    }

    public InvalidTokenException(int errorCode, String errorDesc) {
        super(errorCode, errorDesc);
    }

}
