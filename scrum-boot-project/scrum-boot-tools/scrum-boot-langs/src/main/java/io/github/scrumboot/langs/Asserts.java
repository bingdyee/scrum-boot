package io.github.scrumboot.langs;


import io.github.scrumboot.langs.exception.BusinessExecException;
import io.github.scrumboot.langs.model.status.StatusInfo;

import java.util.Collection;
import java.util.Map;

/**
 * 断言工具
 *
 * @author Bing D. Yee
 * @since 2022/01/12
 */
public final class Asserts {

    private Asserts() { }

    public static void notNull(Object o, StatusInfo info) {
        if (o == null) {
            throw new BusinessExecException(info);
        }
    }

    public static void notNull(Object o, String message) {
        if (o == null) {
            throw new BusinessExecException(message);
        }
    }

    public static void equals(String source, String target, StatusInfo info) {
        if (!source.equals(target)) {
            throw new BusinessExecException(info);
        }
    }

    public static void notEquals(String source, String target, StatusInfo info) {
        if (source.equals(target)) {
            throw new BusinessExecException(info);
        }
    }

    public static void notBlank(String str, StatusInfo info) {
        if (Strings.isBlank(str)) {
            throw new BusinessExecException(info);
        }
    }

    public static void isBlank(String str, StatusInfo info) {
        if (Strings.isNotBlank(str)) {
            throw new BusinessExecException(info);
        }
    }

    public static void isTrue(boolean bool, StatusInfo info) {
        if (!bool) {
            throw new BusinessExecException(info);
        }
    }

    public static void isFalse(boolean bool, StatusInfo info) {
        if (bool) {
            throw new BusinessExecException(info);
        }
    }

    public static void notEmpty(Collection<?> collection, StatusInfo info) {
        if (Collections.isEmpty(collection)) {
            throw new BusinessExecException(info);
        }
    }

    public static void notEmpty(Map<?, ?> map, StatusInfo info) {
        if (Collections.isEmpty(map)) {
            throw new BusinessExecException(info);
        }
    }

}
