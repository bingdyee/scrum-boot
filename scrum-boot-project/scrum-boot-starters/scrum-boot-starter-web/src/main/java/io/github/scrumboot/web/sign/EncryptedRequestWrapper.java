package io.github.scrumboot.web.sign;

import com.fasterxml.jackson.core.type.TypeReference;
import io.github.scrumboot.langs.Jsons;

import javax.servlet.ReadListener;
import javax.servlet.ServletInputStream;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletRequestWrapper;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.nio.charset.StandardCharsets;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;
import java.util.Vector;

/**
 * @author bingdyee
 * @since 2022/04/01
 */
public class EncryptedRequestWrapper extends HttpServletRequestWrapper {

    private static final String SIGN_KEY = "sign";

    private static final String DATA_KEY = "data";

    private final Map<String, String[]> parameterMap;

    private byte[] body;

    public EncryptedRequestWrapper(HttpServletRequest request, byte[] body, HttpMessageEncryptor encryptor) {
        super(request);
        this.body = body;
        this.parameterMap = new HashMap<>(request.getParameterMap());
        if (body != null && body.length > 0) {
            String json = new String(body, StandardCharsets.UTF_8);
            EncryptedBody data = Jsons.toObject(json, EncryptedBody.class);
            this.body = encryptor.decode(data.getData());
            System.err.println(new String(this.body, StandardCharsets.UTF_8));
        } else if (parameterMap.containsKey(SIGN_KEY) && parameterMap.containsKey(DATA_KEY)) {
            String signedData = getParameter(DATA_KEY);
            String plain = new String(encryptor.decode(signedData), StandardCharsets.UTF_8);
            Map<String, String> map = Jsons.toObject(plain, new TypeReference<>() {});
            map.forEach((key, val) -> this.parameterMap.put(key, new String[]{val}));
        }
    }

    @Override
    public Enumeration<String> getParameterNames() {
        Vector<String> vector = new Vector<>(parameterMap.keySet());
        return vector.elements();
    }

    @Override
    public String getParameter(String name) {
        String[] results = parameterMap.get(name);
        return results[0];
    }

    @Override
    public String[] getParameterValues(String name) {
        return parameterMap.get(name);
    }

    @Override
    public Map<String, String[]> getParameterMap() {
        return parameterMap;
    }

    @Override
    public ServletInputStream getInputStream() {
        ByteArrayInputStream inputStream = new ByteArrayInputStream(body);
        return new ServletInputStream() {
            @Override
            public boolean isFinished() {
                return !isReady();
            }

            @Override
            public boolean isReady() {
                return inputStream.available() > 0;
            }

            @Override
            public void setReadListener(ReadListener readListener) {}

            @Override
            public int read() {
                return inputStream.read();
            }

            @Override
            public void close() throws IOException {
                super.close();
                inputStream.close();
            }

        };
    }

}
