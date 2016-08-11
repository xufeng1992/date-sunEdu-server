package com.cn.mogo.sunEdu.core.model.params;

import com.cn.mogo.sunEdu.core.common.Page;

import java.io.Serializable;

/**
 * Created by FE on 2016/6/24.
 */
public class StudentParams extends Page implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 6302663668850844794L;
	private String token;
    private Integer workId;
    private Integer studentId;
    private int specStatus;

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public int getSpecStatus() {
        return specStatus;
    }

    public void setSpecStatus(int specStatus) {
        this.specStatus = specStatus;
    }
}
