package com.cn.mogo.sunEdu.core.model.params;

import com.cn.mogo.sunEdu.core.common.Page;

import java.io.Serializable;

/**
 * Created by FE on 2016/6/25.
 */
public class BasicParams extends Page implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 7202367512640600959L;
    private String token;
    private String msg;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean verifyParams(){
        return false ;
    }
}
