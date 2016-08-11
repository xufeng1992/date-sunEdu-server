package com.cn.mogo.sunEdu.App.json.model;/**
 * Created by Administrator on 2016/6/22 0022.
 */

import java.util.Date;

/**
 * HomeworkCollectBean
 *
 * @author xufeng
 * @date 2016/6/22 0022
 */
public class HomeworkCollectBean {
    private Integer id;

    private String homeworkName;

    private Date pubdate;

    private Date deadline;

    private String homeworkIds;

    private Byte status;

    private Integer createrId;

    private Integer groupId;

    private String studentIds;

    //题数
    private Integer totalNumber;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName == null ? null : homeworkName.trim();
    }

    public Date getPubdate() {
        return pubdate;
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getHomeworkIds() {
        return homeworkIds;
    }

    public void setHomeworkIds(String homeworkIds) {
        this.homeworkIds = homeworkIds == null ? null : homeworkIds.trim();
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds == null ? null : studentIds.trim();
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }
}
