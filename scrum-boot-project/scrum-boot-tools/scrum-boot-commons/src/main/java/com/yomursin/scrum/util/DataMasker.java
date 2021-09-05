package com.yomursin.scrum.util;

/**
 * 脱敏工具类
 *
 * @author yubinbin
 * @since 2021/04/09
 */
public final class DataMasker {

    public static final String MASKER = "*";

    public static final String EMAIL_MASK = "(^\\w{2})[^@]*(@.*$)";

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

    /**
     * 邮箱脱敏
     *
     * @param email
     * @return
     */
    public static String maskEmail(String email) {
        return email.replaceAll(EMAIL_MASK, "$1******$2");
    }

    /**
     * 姓名脱敏
     *
     * @param name
     * @return
     */
    public static String maskName(String name) {
        char[] chars = name.toCharArray();
        if (chars.length == 1) {
            return name;
        } else if (chars.length == 2) {
            return chars[0] + MASKER;
        } else {
            return chars[0] + MASKER + chars[chars.length - 1];
        }
    }

    public static String mask(String value, int left, int right, String masker) {
        if (StringUtils.isBlank(value)) {
            throw new IllegalArgumentException();
        }
        StringBuilder rs = new StringBuilder();
        for (int i = 0, n = value.length(); i < n; ++i) {
            rs.append(i < left || i > n - right - 1 ? value.charAt(i) : masker);
        }
        return rs.toString();
    }

}
