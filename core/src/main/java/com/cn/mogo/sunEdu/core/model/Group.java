package com.cn.mogo.sunEdu.core.model;

import java.util.Date;

public class Group {
    //群组Id
    private Integer groupId;
    //创建人
    private Integer createPeople;
    //群名称
    private String groupName;
    //简介
    private String announcement;
    //（预留字段）
    private String classMessage;
    //群状态
    private Byte groupStatus;
    //创建时间
    private Date createTime;

    public Group(){
        super();
    }
    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer getCreatePeople() {
        return createPeople;
    }

    public void setCreatePeople(Integer createPeople) {
        this.createPeople = createPeople;
    }

    public String getAnnouncement() {
        return announcement;
    }

    public void setAnnouncement(String announcement) {
        this.announcement = announcement == null ? null : announcement.trim();
    }

    public Byte getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Byte groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getClassMessage() {
        return classMessage;
    }

    public void setClassMessage(String classMessage) {
        this.classMessage = classMessage;
    }
}