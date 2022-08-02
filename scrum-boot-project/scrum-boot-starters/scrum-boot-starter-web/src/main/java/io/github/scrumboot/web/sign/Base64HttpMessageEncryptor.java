package io.github.scrumboot.web.sign;

import io.github.scrumboot.langs.Jsons;
import org.springframework.stereotype.Component;

import java.nio.charset.StandardCharsets;
import java.util.Base64;

/**
 * @author Bing D. Yee
 * @since 2022/04/01
 */
@Component
public class Base64HttpMessageEncryptor implements HttpMessageEncryptor {

    @Override
    public String encode(Object obj) {
        return Base64.getEncoder().encodeToString(Jsons.toJson(obj).getBytes(StandardCharsets.UTF_8));
    }

    @Override
    public byte[] decode(String data) {
        return Base64.getDecoder().decode(data.getBytes(StandardCharsets.UTF_8));
    }

}
