package com.cn.mogo.sunEdu.core.model.vo;

import java.io.Serializable;

/**
 * Created by FE on 2016/6/23.
 * 学生作业信息对象
 */
public class StudentVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7709874265383488187L;
	private Integer homeworkId;      //作业集编号
    private Integer checkedNum;       //已经批阅数
    private Integer commitNum;       //上交作业总数
    private String studentName;     //学生姓名
    private Integer studentId;      //学生编号
    private Integer totalNum;       //总题目数量


    public Integer getCheckedNum() {
        return checkedNum;
    }

    public void setCheckedNum(Integer checkedNum) {
        this.checkedNum = checkedNum;
    }

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

    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public Integer getCommitNum() {
        return commitNum;
    }

    public void setCommitNum(Integer commitNum) {
        this.commitNum = commitNum;
    }

    public Integer getTotalNum() {
        return totalNum;
    }

    public void setTotalNum(Integer totalNum) {
        this.totalNum = totalNum;
    }
}
