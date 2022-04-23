package io.github.scrumboot.langs.eventbus;

import io.github.scrumboot.langs.Asserts;

import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArrayList;

/**
 * @author Bing D. Yee
 * @since 2022/04/19
 */
public class EventBus {

    private static final Map<Class<? extends DomainEvent>, CopyOnWriteArrayList<ProxiedMethod>> EVENT_MAP = new ConcurrentHashMap<>();

    public void register(Object obj) {
        Class<?> clazz = obj.getClass();
        for (Method method : clazz.getDeclaredMethods()) {
            if (method.getParameterTypes().length == 1 && method.isAnnotationPresent(Subscriber.class)) {
                final Class<? extends DomainEvent> parameterType = (Class<? extends DomainEvent>) method.getParameterTypes()[0];
                final List<ProxiedMethod> methods = EVENT_MAP.getOrDefault(parameterType, new CopyOnWriteArrayList<>());
                ProxiedMethod proxiedMethod = new ProxiedMethod(obj, method);
                if (!methods.contains(proxiedMethod)) {
                    methods.add(proxiedMethod);
                }
            }
        }
    }


}
