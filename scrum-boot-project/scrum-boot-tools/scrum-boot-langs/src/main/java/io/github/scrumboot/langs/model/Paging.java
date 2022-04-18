package io.github.scrumboot.langs.model;


import com.fasterxml.jackson.annotation.JsonIgnore;

import java.util.Collections;
import java.util.Iterator;
import java.util.List;

/**
 * 分页返回参数
 *
 * @author Bing D. Yee
 * @since 2022/01/12
 */
public class Paging<E> implements Iterable<E>{

    private final long totalElements;
    private final long page;
    private final long size;
    private final List<E> content;
    @JsonIgnore
    private int sortBy;

    public Paging() {
        this(Collections.emptyList(), 0L, 10L, 0L);
    }

    public Paging(long page, long size) {
        this(Collections.emptyList(), page, size, 0L);
    }

    public Paging(long page, long size, int sortBy) {
        this(Collections.emptyList(), page, size, 0L);
        this.sortBy = sortBy;
    }

    public Paging(List<E> content, long page, long size, long totalElements) {
        this.content = Collections.unmodifiableList(content);
        this.totalElements = totalElements;
        this.page = page;
        this.size = size;
        this.sortBy = 1;
    }

    public static <T> Paging<T> of(List<T> content, long page, long size, long totalElements) {
        return new Paging<>(content, page, size, totalElements);
    }

    public static <T> Paging<T> of(long page, long size) {
        return new Paging<>(page, size);
    }

    public static <T> Paging<T> of(long page, long size, int sortBy) {
        return new Paging<>(page, size, sortBy);
    }

    public static <T> Paging<T> empty() {
        return new Paging<>();
    }

    public Integer getTotalPages() {
        return this.getSize() == 0 ? 1 : (int) Math.ceil((double) this.totalElements / (double) getSize());
    }

    @Override
    public Iterator<E> iterator() {
        return this.content.iterator();
    }

    @JsonIgnore
    public boolean isFirstPage() {
        return this.page == PageQuery.FIRST_PAGE;
    }

    public long getTotalElements() {
        return totalElements;
    }

    public long getPage() {
        return page;
    }

    public long getSize() {
        return size;
    }

    public List<E> getContent() {
        return content;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

}
