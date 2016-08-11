package com.cn.mogo.sunEdu.core.model.params.student;

import java.io.Serializable;
import java.util.Date;

/**
 * Created by FE on 2016/7/6.
 */
public class UpdateAnswerParams implements Serializable {

    private Integer answerId;           //答案编号
    private Integer studentId;          //学生编号
    private Integer workId;             //作业编号
    private Integer subjectId;          //题目编号
    private String picAddress;          //答案图片
    private Integer answerStatus = 1;   //答案状态 默认上传之后就是已经完成
    private Date submitTime;            //提交时间

    public Integer getAnswerId() {
        return answerId;
    }

    public void setAnswerId(Integer answerId) {
        this.answerId = answerId;
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

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress;
    }

    public Integer getAnswerStatus() {
        return answerStatus;
    }

    public void setAnswerStatus(Integer answerStatus) {
        this.answerStatus = answerStatus;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Date getSubmitTime() {
        return submitTime;
    }

    public void setSubmitTime(Date submitTime) {
        this.submitTime = submitTime;
    }

    @Override
    public String toString() {
        return "UpdateAnswerParams{" +
                "answerId=" + answerId +
                ", studentId=" + studentId +
                ", workId=" + workId +
                ", subjectId=" + subjectId +
                ", picAddress='" + picAddress + '\'' +
                ", answerStatus=" + answerStatus +
                ", submitTime=" + submitTime +
                '}';
    }
}
