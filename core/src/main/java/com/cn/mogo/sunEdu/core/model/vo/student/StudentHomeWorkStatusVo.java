package com.cn.mogo.sunEdu.core.model.vo.student;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * Created by FE on 2016/7/5.
 * 界面显示学生作业对象
 */
public class StudentHomeWorkStatusVo implements Serializable {



    /**
	 * 
	 */
	private static final long serialVersionUID = 5082123699489616622L;
	private Integer workId;   //作业编号
    private String homeworkName; //作业名称
    private Integer totalNumber; //总题目数
    private Date deadline;       //截至时间
    private String deadlineStr;    //界面截至时间
    private String studentIds;
    private String groupIds;        //学生在群组
    private Integer totalAnswerCommitNum;  //该学生上交答案数
    private Integer checkedAnswerNum;      //答案被批改数
    private Integer workStatus;            //当前作业的状态信息
    private String subCollectionIds;       //改作业集

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    public Integer getTotalAnswerCommitNum() {
        return totalAnswerCommitNum;
    }

    public void setTotalAnswerCommitNum(Integer totalAnswerCommitNum) {
        this.totalAnswerCommitNum = totalAnswerCommitNum;
    }

    public Integer getCheckedAnswerNum() {
        return checkedAnswerNum;
    }

    public void setCheckedAnswerNum(Integer checkedAnswerNum) {
        this.checkedAnswerNum = checkedAnswerNum;
    }

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public String getDeadlineStr() {
        return deadlineStr;
    }

    public void setDeadlineStr(String deadlineStr) {
        this.deadlineStr = deadlineStr;
    }

    public Integer getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(Integer workStatus) {
        this.workStatus = workStatus;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getGroupIds() {
        return groupIds;
    }

    public void setGroupIds(String groupIds) {
        this.groupIds = groupIds;
    }

    public Integer getCollectionStatus() {
        if(StringUtils.isBlank(subCollectionIds)){
            return 0; //该作业没有被该学生收藏
        }
        return 1; //该作业被该学生收藏
    }

    public Set<String> getSubCollectionIds() {
        if(StringUtils.isNotBlank(subCollectionIds)){
            return new HashSet<String>(Arrays.asList(this.studentIds.split(",")));
        }
        return new HashSet<String>();
    }

    public void setSubCollectionIds(String subCollectionIds) {
        this.subCollectionIds = subCollectionIds;
    }

    public int getOddDays() {
        int oddDays = 0;
        long to = this.deadline.getTime();
        long from = new Date().getTime();
        oddDays = (int) (to - from) / (1000 * 60 * 60 * 24);
        return oddDays + 7;
    }
}
