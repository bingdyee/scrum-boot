package com.rechaox.scrum.http.request;

import com.google.common.base.Joiner;

import java.net.URI;
import java.net.URLEncoder;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.nio.charset.StandardCharsets;
import java.time.Duration;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Bing D. Yee
 * @since 2021/10/08
 */
public class GetRequest extends BaseRequest {

    public GetRequest(String uri) {
        super(uri);
    }

    @Override
    public HttpRequest createHttpRequest() {
        List<String> queryParams = this.parameters
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.toList());
        String uri = queryParams.isEmpty() ? requestUri : requestUri + "?" + URLEncoder.encode(Joiner.on("&").join(queryParams), StandardCharsets.UTF_8);
        System.err.println("请求路径：" + uri);
        HttpRequest.Builder builder = HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(5))
                .uri(URI.create(uri))
                .GET();
        this.headers.forEach(builder::setHeader);
        return builder.build();
    }

}
