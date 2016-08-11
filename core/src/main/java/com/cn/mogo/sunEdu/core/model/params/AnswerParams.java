package com.cn.mogo.sunEdu.core.model.params;

import java.io.Serializable;

/**
 * Created by FE on 2016/6/23.
 */
public class AnswerParams extends BasicParams implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 5808142762557616503L;
	private String token;
    private Integer workId;
    private Integer studentId;
    private String msg ;

    public String getMsg() {
        return msg;
    }

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

    public boolean verifyParams(){
        if( workId == null || workId <= 0 ){
            msg = String.format("作业集编号有误,workId = %s" ,workId);
            return false ;
        }
        if( studentId == null || studentId <= 0 ){
            msg = String.format("学生编号有误, studentId = %s" ,studentId);
            return false ;
        }
        return true ;
    }
}
