package com.cn.mogo.sunEdu.App.json.model;/**
 * Created by Administrator on 2016/6/15 0015.
 */

import com.cn.mogo.sunEdu.core.model.Group;

import java.util.Date;

/**
 * StuGroupBean
 *
 * @author xufeng
 * @date 2016/6/15 0015
 */
public class StuGroupBean {
    private Integer id;

    private Integer groupId;

    private Integer studentId;

    private Date applyTime;

    private Date agreeTime;

    private Boolean status;

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

    public Boolean getStatus() {
        return status;
    }

    public void setStatus(Boolean status) {
        this.status = status;
    }
}
