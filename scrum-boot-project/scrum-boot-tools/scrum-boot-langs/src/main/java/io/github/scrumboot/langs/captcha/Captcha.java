package io.github.scrumboot.langs.captcha;

import java.io.ByteArrayOutputStream;
import java.util.Base64;

/**
 *
 *
 * @author Bing D. Yee
 * @date 2020/08/16
 */
public class Captcha {

    private static final String CHAR_SEQUENCE = "ABCDEFGHIJKLMNOPQRSTUVWXYZ01234567890123456789";

    private String chars;
    private CaptchaBuilder builder;

    protected Captcha() {}

    public static CaptchaBuilder builder() {
        return new CaptchaBuilder();
    }

    /**
     * 生成随机验证码
     *
     * @param len
     * @return char[]
     */
    public char[] randomChars(int len) {
        char[] chs = new char[len];
        for (int i = 0; i < len; ++i) {
            chs[i] = CHAR_SEQUENCE.charAt(CaptchaBuilder.random.nextInt(CHAR_SEQUENCE.length()));
        }
        this.chars = new String(chs);
        return chs;
    }

    public String chars() {
        return chars;
    }

    public String toBase64() {
        ByteArrayOutputStream out = this.builder.createToCache(chars.toCharArray());
        return Base64.getEncoder().encodeToString(out.toByteArray());
    }

    public void toFile(String path) {
        this.builder.createToFile(path, this);
    }

    protected void setCaptchaBuilder(CaptchaBuilder builder) {
        this.builder = builder;
    }

}
