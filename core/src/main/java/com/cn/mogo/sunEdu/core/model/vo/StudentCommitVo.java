package com.cn.mogo.sunEdu.core.model.vo;

import java.io.Serializable;

/**
 * Created by FE on 2016/6/23.
 * 提交作业学生作业信息对象
 */
public class StudentCommitVo implements Serializable {

	private static final long serialVersionUID = 7709874265383488187L;
    private String studentName;     //学生姓名
    private Integer studentId;      //学生编号

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
