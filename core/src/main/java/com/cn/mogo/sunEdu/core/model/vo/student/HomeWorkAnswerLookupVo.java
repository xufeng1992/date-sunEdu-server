package com.cn.mogo.sunEdu.core.model.vo.student;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by FE on 2016/7/7.
 * 学生查询老师发布答案的返回数据对象
 */
public class HomeWorkAnswerLookupVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -549754889275605142L;
	private Integer workId;             //作业编号
    private String homeworkName;        //名称
    private Date deadline;              //截至时间
    private Integer totalNumber;        //总题目数
    private Integer teacherAnswerStatus;//老师发布答案状态
    private String answersUrl;          //答案地址
    private Date uploadTime;            //发布答案时间

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

    public String getDeadline() {
        if( this.deadline == null ){
            return "1970-01-01 12:00:00";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.deadline);
    }

    public Date getDeadlineDate() {
        return this.deadline;
    }

    public int getOddDays() {
        long to = this.deadline.getTime();
        long from = new Date().getTime();
        return ((int) (to - from) / (1000 * 60 * 60 * 24) ) + 7;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public Integer getTeacherAnswerStatus() {
        return teacherAnswerStatus;
    }

    public void setTeacherAnswerStatus(Integer teacherAnswerStatus) {
        this.teacherAnswerStatus = teacherAnswerStatus;
    }

    public void setAnswersUrl(String answersUrl) {
        this.answersUrl = answersUrl;
    }

    public Date getUploadTime() {
        return uploadTime;
    }

    public void setUploadTime(Date uploadTime) {
        this.uploadTime = uploadTime;
    }

    public List<String> getAnswerUrlList() {
        List<String> userList = new ArrayList<String>();
        if(StringUtils.isNoneBlank(this.answersUrl)){
            for (String s : this.answersUrl.split(";")){
                if( StringUtils.isNotBlank(s) && !userList.contains(StringUtils.trim(s))){
                    userList.add(StringUtils.trim(s));
                }
            }
        }
        return userList;
    }



}
