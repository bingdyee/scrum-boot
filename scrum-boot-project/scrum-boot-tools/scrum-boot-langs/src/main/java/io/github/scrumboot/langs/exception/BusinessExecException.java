package io.github.scrumboot.langs.exception;


import io.github.scrumboot.langs.model.status.StatusCode;
import io.github.scrumboot.langs.model.status.StatusInfo;

/**
 * 业务执行异常
 *
 * @author Bing D. Yee
 * @since 2022/01/12
 */
public class BusinessExecException extends AbstractWebException {

    private static final long serialVersionUID = -7032134959304939397L;

    public BusinessExecException(String message) {
        super(StatusCode.BUSINESS_ERROR.getCode(), message);
    }

    public BusinessExecException(StatusInfo errorStatus) {
        super(errorStatus);
    }

    public BusinessExecException(int errorCode, String errorDesc) {
        super(errorCode, errorDesc);
    }

}
