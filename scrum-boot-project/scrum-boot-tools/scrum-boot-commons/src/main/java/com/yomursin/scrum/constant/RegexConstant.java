package com.yomursin.scrum.constant;

/**
 *
 *
 * @author Bing D. Yee
 * @date 2020/08/16
 */
public final class RegexConstant {

    public static final String ID_REG = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
    public static final String MOBILE_REG = "^1[345789]\\d{9}$";
    public static final String EMAIL_REG = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    public static final String USERNAME_REG = "^[A-Za-z_@.]{3,20}$";

    private RegexConstant() {}

}
