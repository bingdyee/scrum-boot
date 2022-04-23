package io.github.scrumboot.langs.eventbus;

import java.lang.reflect.Method;

/**
 * @author Bing D. Yee
 * @since 2022/04/19
 */
public class ProxiedMethod {

    private final Object object;
    private final Method method;

    public ProxiedMethod(Object object, Method method) {
        this.object = object;
        this.method = method;
    }

    public Object getObject() {
        return object;
    }

    public Method getMethod() {
        return method;
    }

}
