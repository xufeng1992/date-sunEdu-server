package com.cn.mogo.sunEdu.core.model;

import java.util.Date;

public class Answers {
    //答题集Id
    private Integer id;
    //作业集Id
    private Integer hwcId;
    //老师Id
    private Integer teacherId;
    //群组Id
    private String groupIds;
    //答案路径
    private String answersUrl;
    //上传时间
    private Date uploadTime;
    //状态
    private byte status;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getHwcId() {
        return hwcId;
    }

    public void setHwcId(Integer hwcId) {
        this.hwcId = hwcId;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getAnswersUrl() {
        return answersUrl;
    }

    public void setAnswersUrl(String answersUrl) {
        this.answersUrl = answersUrl == null ? null : answersUrl.trim();
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public byte getStatus() {
        return status;
    }

    public void setStatus(byte status) {
        this.status = status;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }
}