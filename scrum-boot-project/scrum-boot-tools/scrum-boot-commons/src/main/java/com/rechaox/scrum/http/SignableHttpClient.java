package com.rechaox.scrum.http;

import com.rechaox.scrum.http.request.BaseRequest;
import com.rechaox.scrum.http.request.GetRequest;
import com.rechaox.scrum.http.request.PostRequest;

import javax.net.ssl.SSLContext;
import javax.net.ssl.TrustManager;
import javax.net.ssl.X509TrustManager;
import java.io.IOException;
import java.net.http.HttpClient;
import java.net.http.HttpRequest;
import java.net.http.HttpResponse;
import java.security.KeyManagementException;
import java.security.NoSuchAlgorithmException;
import java.security.cert.CertificateException;
import java.security.cert.X509Certificate;
import java.time.Duration;
import java.util.Map;
import java.util.Properties;
import java.util.concurrent.Executors;

/**
 * @author Bing D. Yee
 * @since 2021/10/08
 */
public class SignableHttpClient {

    public final String baseUri;
    public final String appKey;
    public final String appSecret;
    public final HttpClient httpClient;

    public SignableHttpClient(String baseUri, String appKey, String appSecret) {
        this.baseUri = baseUri;
        this.appKey = appKey;
        this.appSecret = appSecret;
        this.httpClient = HttpClient.newBuilder()
                .version(HttpClient.Version.HTTP_2)
                .connectTimeout(Duration.ofSeconds(5))
                .followRedirects(HttpClient.Redirect.NEVER)
                .sslContext(getSslContext())
                .build();
    }

    public String execute(BaseRequest request) {
        try {
            return httpClient.send(request.createHttpRequest(), HttpResponse.BodyHandlers.ofString()).body();
        } catch (IOException | InterruptedException e) {
            throw new ServiceInvokeException(e.getMessage());
        }
    }

    public static SSLContext getSslContext() {
        Properties props = System.getProperties();
        props.setProperty("jdk.internal.httpclient.disableHostnameVerification", "true");
        TrustManager[] trustAllCerts = new TrustManager[1];
        TrustManager tm = new TrustAllManager();
        trustAllCerts[0] = tm;
        SSLContext sc;
        try {
            sc = SSLContext.getInstance("SSL");
            sc.init(null, trustAllCerts, null);
            return sc;
        } catch (NoSuchAlgorithmException | KeyManagementException e) {
            return null;
        }
    }

    static class TrustAllManager implements TrustManager, X509TrustManager {

        @Override
        public void checkClientTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public void checkServerTrusted(X509Certificate[] chain, String authType) throws CertificateException {

        }

        @Override
        public X509Certificate[] getAcceptedIssuers() {
            return null;
        }

    }


    public static void main(String[] args) {
        SignableHttpClient httpClient = new SignableHttpClient("https://www.fastmock.site", "key", "secret");
        GetRequest getRequest = new GetRequest( httpClient.baseUri + "/mock/3af82c1d69bd759f62f13d9b3f893a39/api/v1/get");
        getRequest.addParameter("appKey", httpClient.appKey);
        getRequest.addParameter("sign", "f");
        getRequest.addParameter("requestTime", System.currentTimeMillis() + "");
        String resp = httpClient.execute(getRequest);
        System.err.println(resp);

        PostRequest postRequest = new PostRequest(httpClient.baseUri + "/mock/3af82c1d69bd759f62f13d9b3f893a39/api/v1/post");
        postRequest.addParameter("appKey", httpClient.appKey);
        postRequest.addParameter("sign", "f");
        postRequest.addParameter("requestTime", System.currentTimeMillis() + "");
        resp = httpClient.execute(postRequest);
        System.err.println(resp);
    }

}
