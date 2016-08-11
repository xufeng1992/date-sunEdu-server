package com.cn.mogo.sunEdu.App.json;
/**
 * Created by Administrator on 2016/6/6 0006.
 */

import com.fasterxml.jackson.annotation.JsonInclude;

/**
 * BaseJSONResponse
 * @author xufeng
 * @date 2016/6/6 0006
 */
public class BaseJSONResponse<T> extends Result{
    private static final long serialVersionUID = -4336645848595720624L;
    // 数据
    @JsonInclude(JsonInclude.Include.NON_NULL)
    private T data;

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
