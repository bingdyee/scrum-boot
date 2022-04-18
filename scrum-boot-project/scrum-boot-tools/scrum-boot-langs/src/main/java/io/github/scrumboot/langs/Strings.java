package io.github.scrumboot.langs;

import com.google.common.base.Joiner;
import com.google.common.base.Splitter;

import java.util.List;

/**
 * 字符串工具
 *
 * @author Bing D. Yee
 * @since 2022/01/12
 */
public final class Strings {

    public static final String DEFAULT_SPLIT_SEPARATOR = ",";

    public static boolean isEmpty(final CharSequence cs) {
        return cs == null || cs.length() == 0;
    }

    public static boolean isNotEmpty(final CharSequence cs) {
        return !isEmpty(cs);
    }

    public static boolean isBlank(final CharSequence cs) {
        int strLen;
        if (cs == null || (strLen = cs.length()) == 0) {
            return true;
        }
        for (int i = 0; i < strLen; ++i) {
            if (!Character.isWhitespace(cs.charAt(i))) {
                return false;
            }
        }
        return true;
    }

    public static boolean isNotBlank(final CharSequence cs) {
        return !isBlank(cs);
    }

    public static String trim(final String str) {
        return str == null ? null : str.trim();
    }

    public static String join(Iterable<?> parts, String separator) {
        return Joiner.on(separator).join(parts);
    }

    public static List<String> split(String str) {
        return Splitter.on(DEFAULT_SPLIT_SEPARATOR).splitToList(str);
    }

    private Strings() { }

}
