package com.cn.mogo.sunEdu.core.model.vo.student;

import java.io.Serializable;

/**
 * Created by FE on 2016/7/7.
 * 学生查询答案详细信息数据对象
 */
public class AnswerCheckDetailResultVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -3422094375582587902L;
	private Integer id;
    private Integer studentId;
    private String subjectId;
    private String picAddress;
    private Double score;
    private String teacherVoice;
    private String teacherComment;

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

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
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
}

