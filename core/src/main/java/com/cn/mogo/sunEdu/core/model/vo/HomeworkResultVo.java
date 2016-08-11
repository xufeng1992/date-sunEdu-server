package com.cn.mogo.sunEdu.core.model.vo;

import com.alibaba.druid.util.StringUtils;

import java.io.Serializable;
import java.util.*;

/**
 * Created by FE on 2016/6/22.
 */
public class HomeworkResultVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 8725867734367884241L;
	private Integer id;
    private Integer doneNum;        //上交作业的人数
    private String homeworkName;    //作业名称
    private Date deadline;          //截至日期
    private Integer createrId;
    private Integer totalNumber;
    private String studentIds;
	private int totalStuNum ;
    private int workStatus; //作业状态

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

    public Date getDeadline() {
        return deadline;
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public Integer getCreaterId() {
        return createrId;
    }

    public void setCreaterId(Integer createrId) {
        this.createrId = createrId;
    }

    public Integer getTotalNumber() {
        return totalNumber;
    }

    public void setTotalNumber(Integer totalNumber) {
        this.totalNumber = totalNumber;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    public int getTotalStuNum() {
        if(StringUtils.isEmpty(this.studentIds)){
            return 0 ;
        }
        List<String> userList = new ArrayList<String>(new HashSet<String>(Arrays.asList(this.studentIds.split(","))));
        return userList == null ? 0 : userList.size();
    }

    public Integer getDoneNum() {
        return doneNum;
    }

    public void setDoneNum(Integer doneNum) {
        this.doneNum = doneNum;
    }

    public int getWorkStatus() {
        return workStatus;
    }

    public void setWorkStatus(int workStatus) {
        this.workStatus = workStatus;
    }

    @Override
    public String toString() {
        return "HomeworkResultVo{" +
                "id=" + id +
                ", homeworkName='" + homeworkName + '\'' +
                ", deadline=" + deadline +
                ", createrId=" + createrId +
                ", totalNumber=" + totalNumber +
                '}';
    }
}
