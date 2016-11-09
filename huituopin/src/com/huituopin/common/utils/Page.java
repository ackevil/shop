package com.huituopin.common.utils;

import com.huituopin.common.constants.IntegerConstants;

public class Page {
    public int page = 1;// 当前页

    public int pageSizes = IntegerConstants.DEFINE_PAGE_SIZE;// 每页显示条数

    public int maxRows;// 总条数

    public int maxPage;// 最大页数

    /** default constructor */
    public Page() {

    }

    public int getPage() {
        return page;
    }

    public void setPage(int page) {
        this.page = page;
    }

    public int getPageSizes() {
        return pageSizes;
    }

    public void setPageSizes(int pageSizes) {
        this.pageSizes = pageSizes;
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
