package com.rechaox.scrum.web.entity;


/**
 * @author Bing D. Yee
 * @since 2021/04/05
 */
public class PageQuery {

    private int page = 0;

    private int size = 10;

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getSize() {
        return size;
    }

    public void setSize(int size) {
        this.size = size;
    }
}
