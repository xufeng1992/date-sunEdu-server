package com.cn.mogo.sunEdu.core.model.params.student;

import com.cn.mogo.sunEdu.core.model.params.BasicParams;

public class StudentCollectionParams extends BasicParams {

	private static final long serialVersionUID = -2218167080366539568L;


	private Integer studentId;

	public Integer getStudentId() {
		return studentId;
	}

	public void setStudentId(Integer studentId) {
		this.studentId = studentId;
	}

	public boolean verifyParams() {
		if (studentId == null || studentId < 0) {
			this.setMsg(String.format("学生编号有误 %s", studentId));
			return false;
		}
		return true;
	}

}
