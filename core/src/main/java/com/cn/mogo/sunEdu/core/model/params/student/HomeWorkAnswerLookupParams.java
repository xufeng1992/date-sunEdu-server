package com.cn.mogo.sunEdu.core.model.params.student;

import com.cn.mogo.sunEdu.core.model.params.BasicParams;

import java.io.Serializable;

/**
 * Created by FE on 2016/7/7.
 */
public class HomeWorkAnswerLookupParams extends BasicParams {

    private Integer studentId; //学生编号

    private Integer workId;    //作业编号

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getWorkId() {
        return workId;
    }

    public void setWorkId(Integer workId) {
        this.workId = workId;
    }

    public boolean verifyParams() {
        if( workId == null || workId <= 0){
            this.setMsg(String.format("作业集编号有误, workId = %s" ,workId));
            return false ;
        }
        if( studentId == null || studentId < 0 ){
            this.setMsg(String.format("学生编号有误 %s",studentId));
            return false ;
        }
        return true;
    }
}
