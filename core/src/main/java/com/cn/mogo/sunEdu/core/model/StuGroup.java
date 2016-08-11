package com.cn.mogo.sunEdu.core.model;

import java.util.Date;

public class StuGroup {
    //学生所在群ID
    private Integer id;
    //班群Id
    private Integer groupId;
    //学生Id
    private Integer studentId;
    //申请时间
    private Date applyTime;
    //同意时间
    private Date agreeTime;
    //状态
    private Byte status;

    public Combine getCombine() {
        return combine;
    }

    public void setCombine(Combine combine) {
        this.combine = combine;
    }

    private Combine combine;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Date getApplyTime() {
        return applyTime;
    }

    public void setApplyTime(Date applyTime) {
        this.applyTime = applyTime;
    }

    public Date getAgreeTime() {
        return agreeTime;
    }

    public void setAgreeTime(Date agreeTime) {
        this.agreeTime = agreeTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}