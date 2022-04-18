package io.github.scrumboot.langs.http;

import io.github.scrumboot.langs.Jsons;
import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.client.methods.HttpUriRequest;
import org.apache.http.entity.StringEntity;

import java.nio.charset.StandardCharsets;

/**
 * @author Bing D. Yee
 * @since 2022/03/27
 */
public class PostRequest extends BaseRequest {

    private final RequestMethod method;

    public PostRequest(String uri) {
        this(uri, RequestMethod.POST);
    }

    public PostRequest(String uri, RequestMethod method) {
        super(uri);
        this.method = method;
    }

    @Override
    public HttpUriRequest createHttpRequest() {
        HttpPost httpPost = new HttpPost(getUri());
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(super.getTimeout())
                .setConnectTimeout(super.getTimeout())
                .setConnectionRequestTimeout(super.getTimeout())
                .build();
        httpPost.setConfig(requestConfig);
        if (!bodyParameters.isEmpty()) {
            if (RequestMethod.POST_JSON.equals(method)) {
                String body = Jsons.toJson(bodyParameters);
                httpPost.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
            }
            if (RequestMethod.POST_URLENCODE.equals(method)) {
                String body = getQueryParams(bodyParameters);
                httpPost.setEntity(new StringEntity(body, StandardCharsets.UTF_8));
            }
        }
        this.headers.forEach(httpPost::addHeader);
        return httpPost;
    }

}
