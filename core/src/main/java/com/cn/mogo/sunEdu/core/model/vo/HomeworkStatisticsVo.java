package com.cn.mogo.sunEdu.core.model.vo;

import java.io.Serializable;

/**
 * Created by FE on 2016/7/1.
 */
public class HomeworkStatisticsVo implements Serializable{

    /**
	 * 
	 */
	private static final long serialVersionUID = -4737741861127214092L;
	private Double stuScore;    //学生得分
    private Double totalScore;  //作业总分
    private Double scorePercent; // 学生得分/作业总分=百分比
    private Integer studentId;  //学生编号
    private Integer workId;     //作业集编号
    private String studentIds;  //总人数

    public Double getStuScore() {
        return stuScore;
    }

    public void setStuScore(Double stuScore) {
        this.stuScore = stuScore;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }

    public Double getScorePercent() {
        return scorePercent;
    }

    public void setScorePercent(Double scorePercent) {
        this.scorePercent = scorePercent;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }
}
