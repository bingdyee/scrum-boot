package io.github.scrumboot.langs.http;

import io.github.scrumboot.langs.Jsons;
import org.apache.http.Consts;
import org.apache.http.HttpEntity;
import org.apache.http.HttpResponse;
import org.apache.http.StatusLine;
import org.apache.http.client.HttpResponseException;
import org.apache.http.client.ResponseHandler;
import org.apache.http.config.Registry;
import org.apache.http.config.RegistryBuilder;
import org.apache.http.conn.HttpClientConnectionManager;
import org.apache.http.conn.routing.HttpRoutePlanner;
import org.apache.http.conn.socket.ConnectionSocketFactory;
import org.apache.http.conn.socket.LayeredConnectionSocketFactory;
import org.apache.http.conn.socket.PlainConnectionSocketFactory;
import org.apache.http.conn.ssl.SSLConnectionSocketFactory;
import org.apache.http.impl.client.CloseableHttpClient;
import org.apache.http.impl.client.HttpClientBuilder;
import org.apache.http.impl.client.HttpClients;
import org.apache.http.impl.conn.PoolingHttpClientConnectionManager;
import org.apache.http.util.EntityUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * HTTP Client
 *
 *
 * @author Bing D. Yee
 * @since 2022/03/22
 */
public class ExecutableHttpClient {

    private CloseableHttpClient httpClient;
    private IdleConnectionMonitorThread idleConnectionMonitorThread;
    private String baseUri;
    private long idletime;
    private int validateAfterInactivity;
    private Integer timeout;
    private HttpRoutePlanner routePlanner;
    private boolean inited;
    private final StringResponseHandler responseHandler = new StringResponseHandler(Consts.UTF_8.name());

    public static ExecutableHttpClient newInstance() {
        FactoryHolder.EXECUTABLE_CLIENT.init();
        return FactoryHolder.EXECUTABLE_CLIENT;
    }

    private ExecutableHttpClient() {
        this.idletime = 30L;
        this.validateAfterInactivity = 60000;
        this.inited = false;
    }

    public void init() {
        if (!this.inited) {
            ConnectionSocketFactory plainsf = PlainConnectionSocketFactory.getSocketFactory();
            LayeredConnectionSocketFactory sslsf = SSLConnectionSocketFactory.getSocketFactory();
            Registry<ConnectionSocketFactory> registry = RegistryBuilder.<ConnectionSocketFactory>create()
                    .register("http", plainsf).register("https", sslsf).build();
            PoolingHttpClientConnectionManager cm = new PoolingHttpClientConnectionManager(registry);
            cm.setMaxTotal(800);
            cm.setDefaultMaxPerRoute(400);
            cm.setValidateAfterInactivity(this.validateAfterInactivity);
            HttpClientBuilder httpClientBuilder = HttpClients.custom();
            if (null != this.getRoutePlanner()) {
                httpClientBuilder.setRoutePlanner(this.getRoutePlanner());
            }
            this.httpClient = httpClientBuilder.setConnectionManager(cm).setConnectionManagerShared(true).build();
            this.idleConnectionMonitorThread = new IdleConnectionMonitorThread(cm, this.idletime);
            this.idleConnectionMonitorThread.start();
            this.inited = true;
        }
    }

    public String execute(BaseRequest request) {
        String response;
        try {
            response = this.httpClient.execute(request.createHttpRequest(), responseHandler);
        } catch (IOException e) {
            throw new ServiceInvokeException(e.getMessage());
        }
        return response;
    }

    public void destroy() {
        if (this.idleConnectionMonitorThread != null) {
            this.idleConnectionMonitorThread.shutdown();

            try {
                this.idleConnectionMonitorThread.join();
            } catch (InterruptedException ignored) {}
        }

        if (this.httpClient != null) {
            try {
                this.httpClient.close();
            } catch (IOException ignored) {}
        }

    }

    public Integer getTimeout() {
        return this.timeout;
    }

    public void setTimeout(Integer timeout) {
        this.timeout = timeout;
    }

    public CloseableHttpClient getHttpClient() {
        return this.httpClient;
    }

    public void setBaseUri(String uri) {
        this.baseUri = uri;
    }

    public String getBaseUri() {
        return this.baseUri;
    }

    public long getIdletime() {
        return this.idletime;
    }

    public void setIdletime(long idletime) {
        this.idletime = idletime;
    }

    public HttpRoutePlanner getRoutePlanner() {
        return this.routePlanner;
    }

    public void setRoutePlanner(HttpRoutePlanner routePlanner) {
        this.routePlanner = routePlanner;
    }

    public int getValidateAfterInactivity() {
        return this.validateAfterInactivity;
    }

    public void setValidateAfterInactivity(int validateAfterInactivity) {
        this.validateAfterInactivity = validateAfterInactivity;
    }

    private static class FactoryHolder {
        public static final ExecutableHttpClient EXECUTABLE_CLIENT = new ExecutableHttpClient();
        public static final StringResponseHandler STRING_RESPONSE_HANDLER;
        public static final ByteResponseHandler BYTE_RESPONSE_HANDLER;

        private FactoryHolder() {
        }

        static {
            STRING_RESPONSE_HANDLER = new StringResponseHandler(Consts.UTF_8.name());
            BYTE_RESPONSE_HANDLER = new ByteResponseHandler();
        }
    }

    public static class ByteResponseHandler implements ResponseHandler<byte[]> {
        public ByteResponseHandler() {
        }

        @Override
        public byte[] handleResponse(HttpResponse response) throws IOException {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            response.getEntity().writeTo(bos);
            return bos.toByteArray();
        }
    }

    public static class StringResponseHandler implements ResponseHandler<String> {

        private final String encode;

        public StringResponseHandler(String encode) {
            this.encode = encode;
        }

        @Override
        public String handleResponse(HttpResponse response) throws IOException {
            StatusLine statusLine = response.getStatusLine();
            HttpEntity entity = response.getEntity();
            String responseBody = entity == null ? null : EntityUtils.toString(entity, this.encode);
            if (statusLine.getStatusCode() < 300 && statusLine.getStatusCode() != 203) {
                return responseBody;
            } else {
                EntityUtils.consume(entity);
                throw new HttpResponseException(statusLine.getStatusCode(), responseBody);
            }
        }
    }

    public static class IdleConnectionMonitorThread extends Thread {
        private final HttpClientConnectionManager connMgr;
        private final long idletime;
        private volatile boolean shutdown;

        public IdleConnectionMonitorThread(HttpClientConnectionManager connMgr, long idletime) {
            this.connMgr = connMgr;
            this.idletime = idletime;
        }

        @Override
        public void run() {
            while(true) {
                try {
                    if (!this.shutdown) {
                        synchronized(this) {
                            this.wait(2000L);
                            this.connMgr.closeExpiredConnections();
                            this.connMgr.closeIdleConnections(this.idletime, TimeUnit.SECONDS);
                            continue;
                        }
                    }
                } catch (InterruptedException ignored) { }
                return;
            }
        }

        public void shutdown() {
            this.shutdown = true;
            synchronized(this) {
                this.notifyAll();
            }
        }
    }

    static class ServiceInvokeException extends RuntimeException {
        private static final long serialVersionUID = -2654082656942183861L;

        public ServiceInvokeException() {
            super("调用HTTP请求失败");
        }

        public ServiceInvokeException(String message) {
            super(message);
        }

        public ServiceInvokeException(String message, Throwable cause) {
            super(message, cause);
        }

        public ServiceInvokeException(Throwable cause) {
            super(cause);
        }

        protected ServiceInvokeException(String message, Throwable cause, boolean enableSuppression, boolean writableStackTrace) {
            super(message, cause, enableSuppression, writableStackTrace);
        }
    }

    public <T> T execute(String serverUrl, IHttpRequest<T> request) {
        BaseRequest httpRequest;
        String requestUrl = serverUrl + request.getApiUri();
        switch (request.getRequestMethod()) {
            case GET:
                httpRequest = new GetRequest(requestUrl);
                break;
            case POST:
            case POST_JSON:
            case POST_URLENCODE:
                httpRequest = new PostRequest(requestUrl, request.getRequestMethod());
                break;
            default:
                throw new UnsupportedOperationException("不支持的请求类型 - " + request.getRequestMethod());
        }
        httpRequest.addParameters(request.getRequestParams());
        httpRequest.addHeaders(request.getHeaders());
        String respStr = this.execute(httpRequest);
        return Jsons.toObject(respStr, request.getResponseClass());
    }

}
