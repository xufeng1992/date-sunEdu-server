package com.cn.mogo.sunEdu.core.model.params;

/**
 * Created by FE on 2016/6/28.
 */
public class AnswerAccuracyParams extends BasicParams {

    /**
	 * 
	 */
	private static final long serialVersionUID = -2699365668680800772L;
	private Integer workId; //作业集编号
    private Integer sortStatus = 1; //排序状态值 1按得分升序 ，0 降序

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public Integer getSortStatus() {
        return sortStatus;
    }

    public void setSortStatus(Integer sortStatus) {
        this.sortStatus = sortStatus;
    }
}
