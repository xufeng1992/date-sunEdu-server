package com.cn.mogo.sunEdu.core.common;

/**
 * Created by FE on 2016/6/22.
 */


public class Page {

    /**
     * 查询记录从第几条开始
     */
    private int begin = 0;
    /**
     * 当前页码
     */
    private int currentPage = 0;
    /**
     * 每页记录数
     */
    private int rows = 10;
    /**
     * 总页数
     */
    private int totalPages = 0;
    /**
     * 总记录数
     */
    private int totalRows = 0;

    public Page() {
    }

    public Page(int currentPage, int rows) {
        this.currentPage = currentPage;
        this.rows = rows;
        this.begin = (currentPage - 1) * rows;
    }

    public Page(int currentPage, int rows, int totalRows) {
        this(currentPage, rows);
        setTotalRows(totalRows);
    }

    public int getCurrentPage() {
        return currentPage;
    }

    public void setCurrentPage(Integer currentPage) {

        if (currentPage != null) {
            this.currentPage = Math.max(currentPage, 1);
            this.begin = (this.currentPage - 1) * rows;
        }
    }

    public int getRows() {
        return rows;
    }

    public void setRows(Integer rows) {
        if (rows != null) {
            this.rows = rows;
        }
        setCurrentPage(this.currentPage);
    }

    public int getTotalPages() {
        return totalPages;
    }

    public void setTotalPages(Integer totalPages) {
        if (totalPages != null) {
            this.totalPages = totalPages;
            if (rows > 0 && totalPages > 0) {
                this.totalRows = rows * totalPages;
            }
        }
    }

    public int getTotalRows() {
        return totalRows;
    }

    public void setTotalRows(Integer totalRows) {
        if (totalRows != null) {
            this.totalRows = totalRows;
            if (rows > 0 && totalRows > 0) {
                this.totalPages = (int) (Math.ceil((double) totalRows / rows));
            }
        }
    }

    public int getBegin() {
        return begin;
    }

    public void setBegin(Integer begin) {
        if (begin != null) {
            this.begin = begin;
        }
    }

}
