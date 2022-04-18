package io.github.scrumboot.langs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;
import io.github.scrumboot.langs.model.status.StatusCode;
import io.github.scrumboot.langs.model.status.StatusInfo;

import java.io.Serializable;

/**
 * 接口返回数据封装
 *
 * @author Bing D. Yee
 * @since 2022/01/12
 */
public class ApiResponse<E> implements Serializable {

    private static final long serialVersionUID = 3095433538316185016L;

    private final int code;
    private final String message;
    private final E data;
    private String redirectUri;

    public ApiResponse() {
        this(StatusCode.OK.getCode(), StatusCode.OK.getDesc(), null);
    }

    public ApiResponse(int code, String message, E data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    @JsonIgnore
    public boolean getSuccess() {
        return StatusCode.OK.getCode() == this.code;
    }

    public static <E> ApiResponse<E> ok() {
        return new ApiResponse<>();
    }

    public static <E> ApiResponse<E> ok(E data) {
        return new ApiResponse<>(StatusCode.OK.getCode(), StatusCode.OK.getDesc(), data);
    }

    public static <E> ApiResponse<E> failed(String message) {
        return new ApiResponse<>(StatusCode.BUSINESS_ERROR.getCode(), message, null);
    }

    public static <E> ApiResponse<E> error(String message) {
        return new ApiResponse<>(StatusCode.INTERNAL_SERVER_ERROR.getCode(), message, null);
    }

    public static <E> ApiResponse<E> of(StatusInfo statusInfo) {
        return new ApiResponse<>(statusInfo.getCode(), statusInfo.getDesc(), null);
    }

    public static <E> ApiResponse<E> of(int code, String message) {
        return new ApiResponse<>(code, message, null);
    }

    public int getCode() {
        return code;
    }

    public String getMessage() {
        return message;
    }

    public E getData() {
        return data;
    }

    public String getRedirectUri() {
        return redirectUri;
    }

    public void setRedirectUri(String redirectUri) {
        this.redirectUri = redirectUri;
    }
}
