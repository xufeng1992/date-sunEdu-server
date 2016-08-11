package com.cn.mogo.sunEdu.core.model.vo;

import com.alibaba.druid.util.StringUtils;

import java.io.Serializable;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * Created by FE on 2016/7/4.
 * 查询老师作业列表是返回的数据库对象信息
 */
public class HomeWorkStatusVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 7535265627022704481L;
	private Integer checkedAnswerNum;  //作业集上交答案中已经批改的答案数
    private Integer totalAnswerCommitNum;  //作业集上交答案数
    private Integer homeworkId;     //作业集编号
    private String homeworkName;    //名称
    private Date deadline;          //截至时间
    private Integer teacherId;      //创建人编号，老师编号
    private Integer totalCommitNum; //上交学生个数
    private Integer totalNumber;    //总数
    private String studentIds;      //需要完成该作业的学生编号....
    private int oddDays;            //剩余时间


    public Integer getHomeworkId() {
        return homeworkId;
    }

    public void setHomeworkId(Integer homeworkId) {
        this.homeworkId = homeworkId;
    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public Integer getTotalCommitNum() {
        return totalCommitNum;
    }

    public void setTotalCommitNum(Integer totalCommitNum) {
        this.totalCommitNum = totalCommitNum;
    }

    public String getStudentIds() {
        return studentIds;
    }

    public void setStudentIds(String studentIds) {
        this.studentIds = studentIds;
    }

    public Date getDeadline() {
        return deadline;
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

    public String getDeadlineStr() {
        if (this.deadline != null) {
            return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").format(this.deadline);
        } else {
            return "1970-01-01 00:00:00";
        }
    }

    public int getOddDays() {
        oddDays = 0;
        long to = this.deadline.getTime();
        long from = new Date().getTime();
        oddDays = (int) (to - from) / (1000 * 60 * 60 * 24);
        return oddDays + 7;
    }

    public void setOddDays(int oddDays) {
        this.oddDays = oddDays;
    }

    /**
     * 需要完成该作业人数
     *
     * @return
     */
    public int getTotalStuNum() {
        if (StringUtils.isEmpty(this.studentIds)) {
            return 0;
        }
        List<String> userList = new ArrayList<String>(new HashSet<String>(Arrays.asList(this.studentIds.split(","))));
        return userList == null ? 0 : userList.size();
    }

    public Integer getCheckedAnswerNum() {
        return checkedAnswerNum;
    }

    public void setCheckedAnswerNum(Integer checkedAnswerNum) {
        this.checkedAnswerNum = checkedAnswerNum;
    }

    public Integer getTotalAnswerCommitNum() {
        return totalAnswerCommitNum;
    }

    public void setTotalAnswerCommitNum(Integer totalAnswerCommitNum) {
        this.totalAnswerCommitNum = totalAnswerCommitNum;
    }
}
