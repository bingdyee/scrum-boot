package io.github.scrumboot.web.sign;

/**
 * @author Bing D. Yee
 * @since 2022/04/01
 */
public interface HttpMessageEncryptor {

    String encode(Object obj);

    byte[] decode(String data);

}
