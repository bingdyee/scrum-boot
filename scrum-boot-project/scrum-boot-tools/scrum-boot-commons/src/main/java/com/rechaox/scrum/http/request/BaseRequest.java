package com.rechaox.scrum.http.request;

import com.rechaox.scrum.util.StringUtils;

import java.net.http.HttpRequest;
import java.util.HashMap;
import java.util.Map;
import java.util.TreeMap;

/**
 * @author Bing D. Yee
 * @since 2021/10/08
 */
public abstract class BaseRequest {

    protected Map<String, String> headers;
    protected Map<String, String> parameters;
    protected final String requestUri;

    public BaseRequest(String uri) {
        this.requestUri = uri;
        this.headers = new HashMap<>();
        this.parameters = new HashMap<>();
    }

    public void setHeaders(Map<String, String> headers) {
        if (!headers.isEmpty()) {
            this.headers = headers;
        }
    }

    public void setParameters(Map<String, String> parameters) {
        if (!parameters.isEmpty()) {
            this.parameters = parameters;
        }
    }

    public BaseRequest addHeader(String name, String value) {
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
            this.headers.put(name, value);
        }
        return this;
    }

    public BaseRequest addParameter(String name, String value) {
        if (StringUtils.isNotBlank(name) && StringUtils.isNotBlank(value)) {
            this.parameters.put(name, value);
        }
        return this;
    }

    public abstract HttpRequest createHttpRequest();

}
