package com.cn.mogo.sunEdu.core.model.params;/**
 * Created by Administrator on 2016/6/13 0013.
 */

import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * GroupBean
 *
 * @author xufeng
 * @date 2016/6/13 0013
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class GroupBean {

    private Integer groupId;

    private Integer createPeople;

    private String groupName;

    private String announcement;

    private String classMessage;

    private Boolean groupStatus;

    private Date createTime;
    //班级人数
    private int countPeople;
    //修改类型
    private int type;

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
        this.announcement = announcement;
    }

    public Boolean getGroupStatus() {
        return groupStatus;
    }

    public void setGroupStatus(Boolean groupStatus) {
        this.groupStatus = groupStatus;
    }

    public Date getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Date createTime) {
        this.createTime = createTime;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public int getCountPeople() {
        return countPeople;
    }

    public void setCountPeople(int countPeople) {
        this.countPeople = countPeople;
    }

    public String getClassMessage() {
        return classMessage;
    }

    public void setClassMessage(String classMessage) {
        this.classMessage = classMessage;
    }

    public int getType() {
        return type;
    }

    public void setType(int type) {
        this.type = type;
    }
}
