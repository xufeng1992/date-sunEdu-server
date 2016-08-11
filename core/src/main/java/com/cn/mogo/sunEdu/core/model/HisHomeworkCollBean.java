package com.cn.mogo.sunEdu.core.model;/**
 * Created by Administrator on 2016/6/16 0016.
 */

import java.util.Date;
import java.util.List;

/**
 * HisHomeworkColl
 *
 * @author xufeng
 * @date 2016/6/16 0016
 */
public class HisHomeworkCollBean {

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
    //创建人（老师Id）
    private Integer createrId;
    //班群Ids
    private String groupIds;
    //学生ids
    private String studentIds;
    //作业图片
    private String homeworkPic;
    //作业题数
    private Integer totalNumber;
    //上交人数
    private int finishNum;
    //总人数
    private int studentNum;
    //总分数
    private Integer totalPoints;
    //有效天数
    private int validDay;
    //学生答题集
    private List<AnswerSheet> answerSheetList;

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
        this.homeworkName = homeworkName;
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
        this.homeworkIds = homeworkIds;
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
        this.studentIds = studentIds;
    }

    public int getFinishNum() {
        return finishNum;
    }

    public void setFinishNum(int finishNum) {
        this.finishNum = finishNum;
    }

    public int getStudentNum() {
        return studentNum;
    }

    public void setStudentNum(int studentNum) {
        this.studentNum = studentNum;
    }

    public int getValidDay() {
        return validDay;
    }

    public void setValidDay(int validDay) {
        this.validDay = validDay;
    }

    public int getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public List<AnswerSheet> getAnswerSheetList() {
        return answerSheetList;
    }

    public void setAnswerSheetList(List<AnswerSheet> answerSheetList) {
        this.answerSheetList = answerSheetList;
    }

    public Integer getTotalPoints() {
        return totalPoints;
    }

    public void setTotalPoints(Integer totalPoints) {
        this.totalPoints = totalPoints;
    }

    public String getHomeworkPic() {
        return homeworkPic;
    }

    public void setHomeworkPic(String homeworkPic) {
        this.homeworkPic = homeworkPic;
    }

    //    public HisHomeworkCollBean(Integer id,String homeworkName,Integer totalNumber,String groupIds) {
//        this.id = id;
//        this.homeworkName = homeworkName;
//        this.totalNumber = totalNumber;
//        this.groupIds = groupIds;
//    }
}
