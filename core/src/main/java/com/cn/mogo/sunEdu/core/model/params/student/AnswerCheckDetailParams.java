package com.cn.mogo.sunEdu.core.model.params.student;

import com.cn.mogo.sunEdu.core.model.params.BasicParams;

import java.io.Serializable;

/**
 * Created by FE on 2016/7/7.
 * 学生查询答案详情的入参对象
 */
public class AnswerCheckDetailParams extends BasicParams {

    private Integer id;
    private Integer studentId;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    @Override
    public boolean verifyParams() {
        if (id == null || id < 0) {
            this.setMsg(String.format("答案编号有误, id = %s", id));
            return false;
        }
        if (studentId == null || studentId < 0) {
            this.setMsg(String.format("学生编号有误, studentId = %s", studentId));
            return false;
        }
        return true;
    }
}
