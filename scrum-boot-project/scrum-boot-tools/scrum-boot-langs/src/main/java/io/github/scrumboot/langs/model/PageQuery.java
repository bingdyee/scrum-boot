package io.github.scrumboot.langs.model;

import com.fasterxml.jackson.annotation.JsonIgnore;

import java.io.Serializable;

/**
 * 分页查询
 *
 * @author Bing D. Yee
 * @since 2022/01/12
 */
public class PageQuery implements Serializable {

    public static final int FIRST_PAGE = 1;

    /**
     * 允许最大页大小
     */
    public static final long MAX_SIZE = 100;

    private long page;

    private long size;

    private int sortBy;

    @JsonIgnore
    private long total;

    public PageQuery() {
        this.page = FIRST_PAGE;
        this.size = 10;
        this.sortBy = 1;
    }

    public PageQuery(int page, int size) {
        this.page = page;
        this.size = size > MAX_SIZE ? MAX_SIZE : size;
        this.sortBy = 1;
    }

    public boolean isFirstPage() {
        return this.page == FIRST_PAGE;
    }

    public long getPage() {
        return page;
    }

    public void setPage(long page) {
        this.page = page;
    }

    public long getSize() {
        return size;
    }

    public void setSize(long size) {
        this.size = size;
    }

    public int getSortBy() {
        return sortBy;
    }

    public void setSortBy(int sortBy) {
        this.sortBy = sortBy;
    }

    public long getTotal() {
        return total;
    }

    public void setTotal(long total) {
        this.total = total;
    }
}
