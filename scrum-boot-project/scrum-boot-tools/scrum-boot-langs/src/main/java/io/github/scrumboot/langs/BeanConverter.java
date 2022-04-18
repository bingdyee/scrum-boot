package io.github.scrumboot.langs;

import net.sf.cglib.beans.BeanCopier;
import net.sf.cglib.core.Converter;

import java.beans.PropertyDescriptor;
import java.io.*;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Collectors;

/**
 * 对象转换
 *
 * @author Bing D. Yee
 * @version 2021/04/07
 */
public final class BeanConverter {

    private BeanConverter() { }

    private static final Map<String, BeanCopier> BEAN_COPIER_CACHE = new ConcurrentHashMap<>();

    public static void copyProperties(Object source, Object target) {
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass(), null);
        copier.copy(source, target, null);
    }

    public static void copyIgnoreNullProperties(Object source, Object target) {
        Converter converter = (value, targetClass, context) -> {
            if (value == null) {
                try {
                    String fieldName = ((String) context).substring(3).toLowerCase();
                    PropertyDescriptor descriptor = new PropertyDescriptor(fieldName, target.getClass());
                    value = descriptor.getReadMethod().invoke(target);
                } catch (Exception ignored) {}
            }
            return value;
        };
        BeanCopier copier = getBeanCopier(source.getClass(), target.getClass(), converter);
        copier.copy(source, target, converter);
    }


    public static <T, V> V convert(T source, Class<V> targetClass) {
        Asserts.notNull(source, "Source must not be null");
        Asserts.notNull(targetClass, "Target must not be null");
        return copy(source, targetClass, null);
    }

    public static <T, V> V convert(T source, Class<V> targetClass, Converter converter) {
        Asserts.notNull(source, "Source must not be null");
        Asserts.notNull(targetClass, "Target must not be null");
        return copy(source, targetClass, converter);
    }

    public static <T, V> List<V> convertList(List<T> sourceList, Class<V> targetClass) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        return sourceList.stream()
                .map(source -> convert(source, targetClass))
                .collect(Collectors.toList());
    }

    public static <T, V> List<V> convertList(List<T> sourceList, Class<V> targetClass, Converter converter) {
        if (sourceList == null || sourceList.isEmpty()) {
            return Collections.emptyList();
        }
        return sourceList.stream()
                .map(source -> convert(source, targetClass, converter))
                .collect(Collectors.toList());
    }

    private static <T, V> V copy(T source, Class<V> targetClass, Converter converter) {
        BeanCopier copier = getBeanCopier(source.getClass(), targetClass, converter);
        V target = null;
        try {
            target = targetClass.getDeclaredConstructor().newInstance();
            copier.copy(source, target, converter);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return target;
    }

    private static <T> BeanCopier getBeanCopier(Class<T> source, Class<?> target, Converter converter) {
        String beanKey = source.getName() + target.getName();
        BeanCopier copier = BEAN_COPIER_CACHE.get(beanKey);
        if (copier == null) {
            copier = BeanCopier.create(source, target, converter != null);
            BEAN_COPIER_CACHE.put(beanKey, copier);
        }
        return copier;
    }

    public static <T extends Serializable> T clone(T t) {
        try {
            ByteArrayOutputStream bos = new ByteArrayOutputStream();
            ObjectOutputStream oos = new ObjectOutputStream(bos);
            oos.writeObject(t);
            ByteArrayInputStream bis = new ByteArrayInputStream(bos.toByteArray());
            ObjectInputStream ois = new ObjectInputStream(bis);
            return (T) ois.readObject();
        } catch (IOException | ClassNotFoundException e) {
            e.printStackTrace();
        }
        return null;
    }


}
