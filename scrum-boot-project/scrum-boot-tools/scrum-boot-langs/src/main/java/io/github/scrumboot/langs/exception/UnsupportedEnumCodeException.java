package io.github.scrumboot.langs.exception;

import io.github.scrumboot.langs.model.status.StatusCode;

/**
 * @author bingdyee
 * @since 2022/03/31
 */
public class UnsupportedEnumCodeException extends AbstractWebException {

    public UnsupportedEnumCodeException() {
        super(StatusCode.UN_SUPPORT_ENUM_CODE);
    }

    public UnsupportedEnumCodeException(String message) {
        super(StatusCode.UN_SUPPORT_ENUM_CODE.getCode(), message);
    }

}
