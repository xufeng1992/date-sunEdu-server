package com.cn.mogo.sunEdu.admin.model;

/**
 * Created by chang on 2016/6/3.
 */
public class Admin {
    private Integer id;
    private String username;
    private String password;
    private boolean status;


    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

}
