package com.cn.mogo.sunEdu.App.json;

import java.io.Serializable;

/**
 * Created by Administrator on 2016/6/6 0006.
 */
public class Result implements Serializable {

//    private static final long serialVersionUID = -751082838215080787L;
    /**
     * 信息
     */
    private String message;

    /**
     * 状态码
     */
    private String statusCode;
    /**
     * 是否成功
     */
    private boolean success;
    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getStatusCode() {
        return statusCode;
    }

    public void setStatusCode(String statusCode) {
        this.statusCode = statusCode;
    }

    public Result() {

    }
}
