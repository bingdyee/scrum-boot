package io.github.scrumboot.langs.http;

import org.apache.http.client.config.RequestConfig;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.client.methods.HttpUriRequest;

/**
 * @author Bing D. Yee
 * @since 2022/03/27
 */

public class GetRequest extends BaseRequest {

    public GetRequest(String uri) {
        super(uri);
    }

    @Override
    public HttpUriRequest createHttpRequest() {
        HttpGet httpGet = new HttpGet(getUri());
        RequestConfig requestConfig = RequestConfig.copy(RequestConfig.DEFAULT)
                .setSocketTimeout(super.getTimeout())
                .setConnectTimeout(super.getTimeout())
                .setConnectionRequestTimeout(super.getTimeout())
                .build();
        httpGet.setConfig(requestConfig);
        this.headers.forEach(httpGet::setHeader);
        return httpGet;
    }

}