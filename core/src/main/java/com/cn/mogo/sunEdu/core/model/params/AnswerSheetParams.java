package com.cn.mogo.sunEdu.core.model.params;


/**
 * Created by FE on 2016/6/25.
 */
public class AnswerSheetParams extends BasicParams {
    /**
     *
     */
    private static final long serialVersionUID = -5917218734449374549L;
    private Integer id;
    private Integer studentId;
    private Integer subjectId;
    private String picAddress;
    private Double score;
    private String teacherVoice;
    private String teacherComment;
    private Integer status;


    private String msg;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public static long getSerialVersionUID() {
        return serialVersionUID;
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

    public String getTeacherVoice() {
        return teacherVoice;
    }

    public void setTeacherVoice(String teacherVoice) {
        this.teacherVoice = teacherVoice;
    }

    public String getTeacherComment() {
        return teacherComment;
    }

    public void setTeacherComment(String teacherComment) {
        this.teacherComment = teacherComment;
    }

    public String getMsg() {
        return msg;
    }


    public boolean verifyParams() {
        if (id == null || id <= 0) {
            this.msg = "参数id为空，有错请检查.";
            return false;
        }
        return true;
    }
}
