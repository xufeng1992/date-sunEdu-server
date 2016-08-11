package com.cn.mogo.sunEdu.core.model.vo;

import java.io.Serializable;

/**
 * Created by FE on 2016/6/28.
 */
public class AnswerCheckVo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = 1043252747282971413L;

	private String studentName ;

    private String submitTime ;

    private Integer workId ;

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(String submitTime) {
        this.submitTime = submitTime;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }
}
