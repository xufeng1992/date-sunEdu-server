package com.cn.mogo.sunEdu.core.model.vo.student;

import java.io.Serializable;
import java.util.List;

import com.cn.mogo.sunEdu.core.model.AnswerSheet;

/**
 * @author FE 收藏的作业详细信息返回数据对象
 */
public class StudentCollectionDetailVo implements Serializable {

	private static final long serialVersionUID = 4112457926827361051L;

	private List<AnswerSheet> answerDetailList; // 学生信息列表

	private List<String> anserUrlList; // 答案图片列表

	private List<String> workUrlList; // 题目图片列表

	private String remark;//备注

	public List<AnswerSheet> getAnswerDetailList() {
		return answerDetailList;
	}

	public void setAnswerDetailList(List<AnswerSheet> answerDetailList) {
		this.answerDetailList = answerDetailList;
	}

	public List<String> getAnserUrlList() {
		return anserUrlList;
	}

	public void setAnserUrlList(List<String> anserUrlList) {
		this.anserUrlList = anserUrlList;
	}

	public List<String> getWorkUrlList() {
		return workUrlList;
	}

	public void setWorkUrlList(List<String> workUrlList) {
		this.workUrlList = workUrlList;
	}

	public String getRemark() {
		return remark;
	}

	public void setRemark(String remark) {
		this.remark = remark;
	}
}
