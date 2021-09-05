package com.yomursin.scrum.web.exception;


import com.yomursin.scrum.status.StatusInfo;

/**
 * @author Bing D. Yee
 * @since 2021/04/07
 */
public class ResourceNotFoundException extends AbstractWebException {

    public ResourceNotFoundException(String message) {
        super(message);
    }

    public ResourceNotFoundException(StatusInfo errorStatus) {
        super(errorStatus);
    }

    public ResourceNotFoundException(String errorCode, String errorDesc) {
        super(errorCode, errorDesc);
    }

}

