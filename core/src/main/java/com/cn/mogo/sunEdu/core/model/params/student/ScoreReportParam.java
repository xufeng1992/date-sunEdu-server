package com.cn.mogo.sunEdu.core.model.params.student;/**
 * Created by Administrator on 2016/7/8 0008.
 */

import java.io.Serializable;

/**
 * ScoreReportParam
 *
 * @author xufeng
 * @date 2016/7/8 0008
 */
public class ScoreReportParam implements Serializable {
    //科目Id
    private Integer subjectId;
    //学生Id
    private Integer studentId;
    //准确率
    private double accuracy;
//    //平均准确率
//    private double averageAccuracy;
    //作业名称
    private String homeworkName;

    public ScoreReportParam(String homeworkName,double accuracy ) {
        this.homeworkName = homeworkName;
        this.accuracy = accuracy;
    }
    public ScoreReportParam() {}

    public Integer getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(Integer subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public double getAccuracy() {
        return accuracy;
    }

    public void setAccuracy(double accuracy) {
        this.accuracy = accuracy;
    }

//    public double getAverageAccuracy() {
//        return averageAccuracy;
//    }
//
//    public void setAverageAccuracy(double averageAccuracy) {
//        this.averageAccuracy = averageAccuracy;
//    }

    public String getHomeworkName() {
        return homeworkName;
    }

    public void setHomeworkName(String homeworkName) {
        this.homeworkName = homeworkName;
    }
}
