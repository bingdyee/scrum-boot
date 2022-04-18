package io.github.scrumboot.langs;

/**
 * 脱敏工具类
 *
 * @author Bing D. Yee
 * @since 2021/04/09
 */
public final class DataMasker {

    public static final String MASKER = "*";

    /**
     * 手机号脱敏
     *
     * @param phone
     * @return
     */
    public static String maskPhone(String phone) {
        return mask(phone, 3, 4, MASKER);
    }

    /**
     * 身份证脱敏
     *
     * @param idCard
     * @return
     */
    public static String maskIdCard(String idCard) {
        return mask(idCard, 6, 4, MASKER);
    }

    public static String mask(String value, int left, int right, String masker) {
        if (Strings.isBlank(value)) {
            throw new IllegalArgumentException();
        }
        StringBuilder rs = new StringBuilder();
        for (int i = 0, n = value.length(); i < n; ++i) {
            rs.append(i < left || i > n - right - 1 ? value.charAt(i) : masker);
        }
        return rs.toString();
    }

}
