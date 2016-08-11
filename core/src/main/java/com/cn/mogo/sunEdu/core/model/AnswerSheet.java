package com.cn.mogo.sunEdu.core.model;

public class AnswerSheet {
    //答题表Id
    private Integer id;
    //学生Id
    private Integer studentId;
    //题目编号
    private Integer subjectId;
    //作业集Id
    private Integer subCollect;
    //学生答题图片地址
    private String picAddress;
    //分数
    private Double score;
    //教师语言
    private String teacherVoice;
    //教师批注
    private String teacherComment;
    //答题状态
    private Boolean status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getSubCollect() {
        return subCollect;
    }

    public void setSubCollect(Integer subCollect) {
        this.subCollect = subCollect;
    }

    public String getPicAddress() {
        return picAddress;
    }

    public void setPicAddress(String picAddress) {
        this.picAddress = picAddress == null ? null : picAddress.trim();
    }

    public Double getScore() {
        return score;
    }

    public void setScore(Double score) {
        this.score = score;
    }

    public String getTeacherVoice() {
        return teacherVoice;
    }

    public void setTeacherVoice(String teacherVoice) {
        this.teacherVoice = teacherVoice == null ? null : teacherVoice.trim();
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment == null ? null : teacherComment.trim();
    }

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}