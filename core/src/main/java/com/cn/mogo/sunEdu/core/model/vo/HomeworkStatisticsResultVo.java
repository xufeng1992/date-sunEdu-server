package com.cn.mogo.sunEdu.core.model.vo;

import java.io.Serializable;

/**
 * 学生作业统计返回数据对象
 */
public class HomeworkStatisticsResultVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -8265713220239742195L;
	private Double maxScore;                    //最高得分
    private Double minScore;                    //最低总分
    private Double workScore;                   //作业总分数
    private Integer passStudentNum;             //及格人数
    private Integer totalStudentNum;            //总人数

    public Double getMaxScore() {
        return maxScore;
    }

    public void setMaxScore(Double maxScore) {
        this.maxScore = maxScore;
    }

    public Double getMinScore() {
        return minScore;
    }

    public void setMinScore(Double minScore) {
        this.minScore = minScore;
    }

    public Double getWorkScore() {
        return workScore;
    }

    public void setWorkScore(Double workScore) {
        this.workScore = workScore;
    }

    public Integer getPassStudentNum() {
        return passStudentNum;
    }

    public void setPassStudentNum(Integer passStudentNum) {
        this.passStudentNum = passStudentNum;
    }

    public Integer getTotalStudentNum() {
        return totalStudentNum;
    }

    public void setTotalStudentNum(Integer totalStudentNum) {
        this.totalStudentNum = totalStudentNum;
    }
}