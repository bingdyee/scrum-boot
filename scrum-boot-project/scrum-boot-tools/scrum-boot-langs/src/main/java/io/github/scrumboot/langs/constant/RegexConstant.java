package io.github.scrumboot.langs.constant;

import java.util.regex.Pattern;

/**
 *
 * 正则表达式常量
 *
 * @author Bing D. Yee
 * @since 2022/01/12
 */
public final class RegexConstant {

    public static final Pattern MOBILE_PATTERN = Pattern.compile(RegexConstant.MOBILE_REG);

    /** 身份证 */
    public static final String ID_CARD_REG = "(^[1-9]\\d{5}(18|19|([23]\\d))\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{3}[0-9Xx]$)|(^[1-9]\\d{5}\\d{2}((0[1-9])|(10|11|12))(([0-2][1-9])|10|20|30|31)\\d{2}[0-9Xx]$)";
    /** 手机号 */
    public static final String MOBILE_REG = "^1[345789]\\d{9}$";
    /** 邮箱 */
    public static final String EMAIL_REG = "^[A-Za-z0-9\\u4e00-\\u9fa5]+@[a-zA-Z0-9_-]+(\\.[a-zA-Z0-9_-]+)+$";
    /** HTTP 网络路径 */
    public static final String HTTP_URL_REG = "^https?://\\S{3,255}$";
    /** 网络路径 */
    public static final String URL_REG = "^((mailto\\:|(news|(ht|f)tp(s?))\\://){1}\\S+)$";
    /** 基数 Cardinal numbers */
    public static final String CARDINAL_NUMBER_REG = "^-?[0-9]+(\\.[0-9]+)?$";
    /** Cardinal numbers, optionally thousands are separated by comma. */
    public static final String CARDINAL_NUMBER_WITH_COMMA_REG = "^\\d{1,3},(\\d{3},)*\\d{3}(\\.\\d+)?$";
    /** Int */
    public static final String INTEGER_REG = "[-+]?\\d{1,9}";
    /** Double */
    public static final String DOUBLE_REG = "[-+]?[0-9]*\\.?[0-9]+(?:[eE][-+]?[0-9]+)?";
    /** Boolean */
    public static final String BOOLEAN_REG = "(true|false)";
    /** Long */
    public static final String LONG_REG = "[-+]?\\d{1,19}";
    /** Date */
    public static final String DATE_REG = "\\d{4}(-|\\/)((0[1-9])|(1[0-2]))(-|\\/)((0[1-9])|([1-2][0-9])|(3[0-1]))";
    /** Time */
    public static final String TIME_REG = "(([0-1][0-9])|(2[0-3])):([0-5][0-9])(:([0-5][0-9]))?";
    /** Datetime */
    public static final String DATETIME_REG = "\\d{4}(-|\\/)((0[1-9])|(1[0-2]))(-|\\/)((0[1-9])|([1-2][0-9])|(3[0-1]))(T|\\s)(([0-1][0-9])|(2[0-3])):([0-5][0-9]):([0-5][0-9])";

    private RegexConstant() {
    }

}
