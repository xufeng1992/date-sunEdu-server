package com.cn.mogo.sunEdu.core.model;

import java.util.Date;

public class Homework {
    private Integer id;

    private Date addTime;

    private String homeworkName;

    private Double subjectScore;

    private String answerAddress;

    private String pictureAddress;

    private Integer teacherId;

    private String relatedDiscipline;

    private Byte status;

    private String detail;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName == null ? null : homeworkName.trim();
    }

    public Double getSubjectScore() {
        return subjectScore;
    }

    public void setSubjectScore(Double subjectScore) {
        this.subjectScore = subjectScore;
    }

    public String getAnswerAddress() {
        return answerAddress;
    }

    public void setAnswerAddress(String answerAddress) {
        this.answerAddress = answerAddress == null ? null : answerAddress.trim();
    }

    public String getPictureAddress() {
        return pictureAddress;
    }

    public void setPictureAddress(String pictureAddress) {
        this.pictureAddress = pictureAddress == null ? null : pictureAddress.trim();
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getRelatedDiscipline() {
        return relatedDiscipline;
    }

    public void setRelatedDiscipline(String relatedDiscipline) {
        this.relatedDiscipline = relatedDiscipline == null ? null : relatedDiscipline.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getDetail() {
        return detail;
    }

    public void setDetail(String detail) {
        this.detail = detail == null ? null : detail.trim();
    }
}