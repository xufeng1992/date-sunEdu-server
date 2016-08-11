package com.cn.mogo.sunEdu.core.model.vo;

import java.io.Serializable;

/**
 * Created by FE on 2016/6/24.
 */
public class AnswerVo implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 3224059344182007311L;
    private String subjectId;       //题目编号
    private Integer status;         //状态
    private Integer studentId;      //学生编号
    private String picAddress;     //学生答案图片地址
    private Double score;          //答案分数
    private Double totalScore;    //作业集总分
    private Integer id;             //答案编号

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public Double getTotalScore() {
        return totalScore;
    }

    public void setTotalScore(Double totalScore) {
        this.totalScore = totalScore;
    }
}
