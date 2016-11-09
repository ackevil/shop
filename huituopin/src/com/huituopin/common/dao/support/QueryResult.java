package com.huituopin.common.dao.support;

import java.io.Serializable;
import java.util.List;

/**
 * 分页对象. 包含当前页数据及分页信息,如总记录数.
 */
@SuppressWarnings("serial")
public class QueryResult<T> implements Serializable {

    public static int DEFAULT_PAGE_SIZE = 15;

    private int pageSize = DEFAULT_PAGE_SIZE; // 每页的记录数

    private long start; // 当前页第一条数据在List中的位置,从0开始

    private List<T> resultlist; // 当前页中存放的记录,类型一般为List

    private long totalrecord; // 总记录数

    private long totalpages;// 总页数

    private long currentPageNo;// 当前页号

    private long previousPage;// 上一页

    private long nextPage;// 下一页

    /**
     * 构造方法，只构造空页.
     */
    public QueryResult() {
        this(0, 0, DEFAULT_PAGE_SIZE, null);
    }

    /**
     * 默认构造方法.
     * 
     * @param start
     *            本页数据在数据库中的起始位置
     * @param totalSize
     *            数据库中总记录条数
     * @param pageSize
     *            本页容量
     * @param data
     *            本页包含的数据
     */
    public QueryResult(long start, long totalSize, int pageSize, List data) {
        this.pageSize = pageSize;
        this.start = start;
        this.totalrecord = totalSize;
        this.resultlist = data;
    }

    public long getStart() {
        return start;
    }

    /**
     * 取总记录数.
     */
    public long getTotalrecord() {
        return this.totalrecord;
    }

    public void setTotalrecord(long totalrecord) {
        this.totalrecord = totalrecord;
    }

    /**
     * 取总页数.
     */
    public long getTotalpages() {
        if (totalrecord % pageSize == 0)
            return totalrecord / pageSize;
        else
            return totalrecord / pageSize + 1;
    }

    /**
     * 取每页数据容量.
     */
    public int getPageSize() {
        return pageSize;
    }

    /**
     * 取当前页中的记录.
     */
    public List<T> getResultlist() {
        return resultlist;
    }

    public void setResultlist(List<T> resultlist) {
        this.resultlist = resultlist;
    }

    /**
     * 取该页当前页码,页码从1开始.
     */
    public long getCurrentPageNo() {
        return start / pageSize + 1;
    }

    /**
     * 该页是否有下一页.
     */
    public boolean hasNextPage() {
        return this.getCurrentPageNo() < this.getTotalpages();
    }

    /**
     * 该页是否有上一页.
     */
    public boolean hasPreviousPage() {
        return this.getCurrentPageNo() > 1;
    }

    /**
     * 获取任一页第一条数据在数据集的位置，每页条数使用默认值.
     * 
     * @see #getStartOfPage(int,int)
     */
    protected static int getStartOfPage(int pageNo) {
        return getStartOfPage(pageNo, DEFAULT_PAGE_SIZE);
    }

    /**
     * 获取任一页第一条数据在数据集的位置.
     * 
     * @param pageNo
     *            从1开始的页号
     * @param pageSize
     *            每页记录条数
     * @return 该页第一条数据
     */
    public static int getStartOfPage(int pageNo, int pageSize) {
        return (pageNo - 1) * pageSize;
    }

    public long getPreviousPage() {
        this.previousPage = this.getCurrentPageNo() - 1;
        return this.previousPage > 0 ? this.previousPage : 1;
    }

    public long getNextPage() {
        this.nextPage = this.getCurrentPageNo() + 1;
        return this.nextPage > this.getTotalpages() ? this.getTotalpages() : this.nextPage;
    }
}
