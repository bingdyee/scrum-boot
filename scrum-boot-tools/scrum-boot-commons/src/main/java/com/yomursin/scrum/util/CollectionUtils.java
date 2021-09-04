package com.yomursin.scrum.util;

import java.util.Collection;

/**
 * @author yubinbin
 * @since 2021/04/07
 */
public final class CollectionUtils {

    public static boolean isEmpty(Collection<?> coll) {
        return (coll == null || coll.isEmpty());
    }

    public static boolean isNotEmpty(Collection<?> coll) {
        return !CollectionUtils.isEmpty(coll);
    }

}
