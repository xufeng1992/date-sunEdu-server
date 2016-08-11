package com.cn.mogo.sunEdu.core.model.vo.student;

import org.apache.commons.lang3.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Created by FE on 2016/7/6.
 * 学生端返回作业详情信息
 */
public class HomeworkDetailVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -1968967289963515301L;
	//作业集Id
    private Integer id;
    //作业名称
    private String homeworkName;
    //发布时间
    private Date pubdate;
    //截止时间
    private Date deadline;
    //作业图片
    private String homeworkPic;
    //作业题数
    private Integer totalNumber;
    //总分数
    private Integer totalPoints;
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
        this.homeworkName = homeworkName;
    }

    public String getPubdate() {
        if( this.pubdate == null ){
            return "1970-01-01 12:00:00";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.pubdate);
    }

    public void setPubdate(Date pubdate) {
        this.pubdate = pubdate;
    }

    public String getDeadline() {
        if( this.deadline == null ){
            return "1970-01-01 12:00:00";
        }
        return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.deadline);
    }

    public void setDeadline(Date deadline) {
        this.deadline = deadline;
    }

    public List<String> getHomeworkPicList() {
        List<String> userList = new ArrayList<String>();
        if(StringUtils.isNoneBlank(this.homeworkPic)){
            for (String s : this.homeworkPic.split(";")){
                if( StringUtils.isNotBlank(s) && !userList.contains(StringUtils.trim(s))){
                    userList.add(StringUtils.trim(s));
                }
            }
        }
        return userList;
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
