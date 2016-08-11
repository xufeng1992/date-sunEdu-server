package com.cn.mogo.sunEdu.core.model;

import java.util.Date;

public class HomeworkCollect {
    //作业集Id
    private Integer id;
    //作业名称
    private String homeworkName;
    //发布时间
    private Date pubdate;
    //截止时间
    private Date deadline;
    //作业Ids
    private String homeworkIds;
    //状态
    private Byte status;
    //创建人
    private Integer createrId;
    //班群Ids
    private String groupIds;
    //学生ids
    private String studentIds;
    //作业图片
    private String homeworkPic;
    //作业题数
    private Integer totalNumber;
    //总分数
    private Integer totalPoints;
//    private AnswerSheet answerSheet;
    //备注
    private String remark;

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

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds == null ? null : studentIds.trim();
    }

    public String getHomeworkPic() {
        return homeworkPic;
    }

    public void setHomeworkPic(String homeworkPic) {
        this.homeworkPic = homeworkPic;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getRemark() {
        return remark;
    }

    public void setRemark(String remark) {
        this.remark = remark;
    }
}