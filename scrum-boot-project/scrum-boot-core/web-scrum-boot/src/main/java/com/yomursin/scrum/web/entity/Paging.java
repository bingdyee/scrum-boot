package com.yomursin.scrum.web.entity;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * @author Bing D. Yee
 * @since 2021/04/05
 */
public class Paging<E> implements Iterable<E> {

    private final Long total;

    /** current page */
    private final Integer number;
    /** page size */
    private final Integer size;
    /** page data */
    private final List<E> content;

    public Paging(List<E> content, int size, int number, Long total) {
        this.total = total;
        this.content = content == null ? new ArrayList<>() : content;
        this.size = size;
        this.number = number;
    }

    public static<T> Paging<T> of(List<T> content, int size, int number, Long total) {
        return new Paging<>(content, size, number, total);
    }

    public long getTotalElements() {
        return this.total;
    }

    public int getSize() {
        return this.size;
    }

    public int getNumber() {
        return this.number;
    }

    public List<E> getContent() {
        return Collections.unmodifiableList(content);
    }

    public Integer getTotalPages() {
        return getSize() == 0 ? 1 : (int) Math.ceil((double) total / (double) getSize());
    }

    @Override
    public Iterator<E> iterator() {
        return this.content.iterator();
    }

}
