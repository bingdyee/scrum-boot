package io.github.scrumboot.langs.http;

import com.google.common.base.Strings;
import org.apache.http.client.methods.HttpUriRequest;

import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.util.HashMap;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * @author Bing D. Yee
 * @since 2022/03/27
 */
public abstract class BaseRequest {

    protected final int timeout;
    protected Map<String, String> headers;
    protected Map<String, String> parameters;
    protected Map<String, String> bodyParameters;
    protected final String requestUri;

    public BaseRequest(String uri) {
        this.requestUri = uri;
        this.timeout = 5000;
        this.headers = new HashMap<>();
        this.parameters = new HashMap<>();
        this.bodyParameters = new HashMap<>();
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

    public void setBodyParameters(Map<String, String> parameters) {
        if (!bodyParameters.isEmpty()) {
            this.bodyParameters = parameters;
        }
    }

    public BaseRequest addHeader(String name, String value) {
        if (!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(value)) {
            this.headers.put(name, value);
        }
        return this;
    }

    public BaseRequest addParameter(String name, String value) {
        if (!Strings.isNullOrEmpty(name) && !Strings.isNullOrEmpty(value)) {
            this.parameters.put(name, value);
        }
        return this;
    }

    public void addParameters(Map<String, String> parameters) {
        if (!parameters.isEmpty()) {
            this.parameters.putAll(parameters);
        }
    }

    public void addHeaders(Map<String, String> headers) {
        if (!headers.isEmpty()) {
            this.headers.putAll(headers);
        }
    }

    public int getTimeout() {
        return timeout;
    }

    public String getQueryParams(Map<String, String> parameters) {
        return this.parameters
                .entrySet()
                .stream()
                .map(entry -> URLEncoder.encode(entry.getKey(), StandardCharsets.UTF_8) + "=" +
                        URLEncoder.encode(entry.getValue(), StandardCharsets.UTF_8))
                .collect(Collectors.joining("&"));
    }

    public String getUri() {
        String queryParams = getQueryParams(parameters);
        return queryParams.isEmpty() ? requestUri : requestUri + "?" + queryParams;
    }

    /**
     * 创建请求
     *
     * @return  HTTP请求
     */
    public abstract HttpUriRequest createHttpRequest();

}

