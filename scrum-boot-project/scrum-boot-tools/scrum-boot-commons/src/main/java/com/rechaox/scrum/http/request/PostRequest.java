package com.rechaox.scrum.http.request;

import com.google.common.base.Joiner;
import lombok.extern.slf4j.Slf4j;

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
public class PostRequest extends BaseRequest{

    public PostRequest(String uri) {
        super(uri);
    }

    @Override
    public HttpRequest createHttpRequest() {
        List<String> queryParams = this.parameters
                .entrySet()
                .stream()
                .map(entry -> entry.getKey() + "=" + entry.getValue())
                .collect(Collectors.toList());
        String body = URLEncoder.encode(Joiner.on("&").join(queryParams), StandardCharsets.UTF_8);
        HttpRequest.BodyPublisher publisher = HttpRequest.BodyPublishers.ofString(body);
        System.err.println("请求路径：" + requestUri);
        System.err.println("请求体：" + body);
        return HttpRequest.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .timeout(Duration.ofSeconds(5))
                .uri(URI.create(this.requestUri))
                .POST(publisher).build();
    }

}
