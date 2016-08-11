package com.cn.mogo.sunEdu.core.model.vo.student;

import java.io.Serializable;

/**
 * Created by FE on 2016/7/7.
 * 学生查看一批该作业列表答案返回数据对象
 */
public class AnswerCheckListResultVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = -9040557006506098722L;
	private Integer id;             //答案编号
    private String subjectId;       //题目编号
    private Integer studentId;      //学生编号
    private Integer collectionStatus ; //是否被收藏 0：N 1：Y,

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getSubjectId() {
        return subjectId;
    }

    public void setSubjectId(String subjectId) {
        this.subjectId = subjectId;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getCollectionStatus() {
        return collectionStatus;
    }

    public void setCollectionStatus(Integer collectionStatus) {
        this.collectionStatus = collectionStatus;
    }
}
