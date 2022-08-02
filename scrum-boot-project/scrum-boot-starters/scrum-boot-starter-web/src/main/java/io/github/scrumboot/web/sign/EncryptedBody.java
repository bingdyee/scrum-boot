package io.github.scrumboot.web.sign;

import lombok.Getter;
import lombok.Setter;
import lombok.ToString;

/**
 * @author bingdyee
 * @since 2022/04/01
 */
@Getter
@Setter
@ToString
public class EncryptedBody {

    private String sign;

    private String data;

    private Long timestamp;

    public EncryptedBody() {}

    public EncryptedBody(String sign, String body) {
        this.sign = sign;
        this.data = body;
        this.timestamp = System.currentTimeMillis();
    }

    public static EncryptedBody of(String sign, String body) {
        return new EncryptedBody(sign, body);
    }

}
