package com.cn.mogo.sunEdu.core.model.vo.student;

import com.cn.mogo.sunEdu.core.model.vo.AnswerVo;

import java.io.Serializable;
import java.util.List;

/**
 * Created by FE on 2016/7/6.
 * 学生端返回给界面 修改，发布答案接口的数据对象
 */
public class AnswerUpdateVo implements Serializable {

    /**
	 * 
	 */
	private static final long serialVersionUID = 169130405460522311L;
	private String homeWorkName; //作业名称
    private Integer workId;     //作业编号
    private List<AnswerVo> answerList; //作业答案列表

    public String getHomeWorkName() {
        return homeWorkName;
    }

    public void setHomeWorkName(String homeWorkName) {
        this.homeWorkName = homeWorkName;
    }

    public List<AnswerVo> getAnswerList() {
        return answerList;
    }

    public void setAnswerList(List<AnswerVo> answerList) {
        this.answerList = answerList;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }
}
