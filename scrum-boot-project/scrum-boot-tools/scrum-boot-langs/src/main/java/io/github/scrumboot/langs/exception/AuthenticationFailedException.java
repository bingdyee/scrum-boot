package io.github.scrumboot.langs.exception;


import io.github.scrumboot.langs.model.status.StatusCode;

/**
 * @author bingdyee
 * @since 2022/03/24
 */
public class AuthenticationFailedException extends AbstractWebException {

    public AuthenticationFailedException() {
        super(StatusCode.LOGIN_FAILED.getCode(), "登录失败");
    }

    public AuthenticationFailedException(String message) {
        super(StatusCode.LOGIN_FAILED.getCode(), message);
    }

}
