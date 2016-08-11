package com.cn.mogo.sunEdu.core.model.vo;

import java.io.Serializable;

/**
 * Created by FE on 2016/6/28.
 */
public class AnswerAccuracyVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -185022003526023992L;

	private String studentName;

    private Integer workId;

    private Double totalScore; //作业集总分

    private Double studentScore; //学生得分

    private Integer studentId; //学生编号

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Double getTotalScore() {
        if( totalScore == null ){
            totalScore = 0D;
        }
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getStudentScore() {
        if( studentScore == null ){
            studentScore = 0D ;
        }
        return studentScore;
    }

    public void setStudentScore(Double studentScore) {
        this.studentScore = studentScore;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }
}
