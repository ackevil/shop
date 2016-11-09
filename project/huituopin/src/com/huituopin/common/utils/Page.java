package com.huituopin.common.utils;

import javax.persistence.Entity;

@Entity
public class Page {
    private int page = 1;// 当前页

    private int rows = 10;// 每页显示条数

    private int maxRows;// 总条数

    private int maxPage;// 最大页数

    /** default constructor */
    public Page() {

    }

    public Page(int page, int rows) {
        this.page = page;
        this.rows = rows;
    }

    /** full constructor */
    public Page(int page, int rows, int maxRows, int maxPage) {
        this.page = page;
        this.rows = rows;
        this.maxPage = maxPage;
        this.maxRows = maxRows;
    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getRows() {
        return rows;
    }

    public void setRows(int rows) {
        this.rows = rows;
    }

    public int getMaxRows() {
        return maxRows;
    }

    public void setMaxRows(int maxRows) {
        this.maxRows = maxRows;
    }

    public int getMaxPage() {
        return maxPage;
    }

    public void setMaxPage(int maxPage) {
        this.maxPage = maxPage;
    }

}
