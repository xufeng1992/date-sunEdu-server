package com.cn.mogo.sunEdu.core.common;


/**
 * Created by FE on 2016/6/22.
 */
public class PageableContent<T> extends AbstractPageable {
    private T content;

    private Object extData;

    public PageableContent(T content) {
        this.content = content;
    }

    public PageableContent(T content, int currentPage, int rows, int totalRows) {
        this.content = content;
        super.setCurrentPage(currentPage);
        super.setTotalRows(totalRows);
    }

    public T getContent() {
        return content;
    }

    public void setContent(T content) {
        this.content = content;
    }

    public Object getExtData() {
        return extData;
    }

    public void setExtData(Object extData) {
        this.extData = extData;
    }


}
