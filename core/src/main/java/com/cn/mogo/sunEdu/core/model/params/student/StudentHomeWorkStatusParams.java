package com.cn.mogo.sunEdu.core.model.params.student;

import com.cn.mogo.sunEdu.core.model.params.BasicParams;

/**
 * Created by FE on 2016/7/5.
 * 查询学生作业参数对象
 */
public class StudentHomeWorkStatusParams extends BasicParams {

    private Integer specStatus = 0;
    private Integer studentId;
    private Integer groupId ; //群组编号

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public Integer getSpecStatus() {
        return specStatus;
    }

    public void setSpecStatus(Integer specStatus) {
        this.specStatus = specStatus;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public boolean verifyParams(){
        if( groupId != null && groupId <= 0 ){
            this.setMsg(String.format("学生所在群组编号有误 %s",groupId));
            return false ;
        }
        if( specStatus < 0 ){
            this.setMsg(String.format("查询状态有误 %s",specStatus));
            return false ;
        }
        if( studentId == null || studentId < 0 ){
            this.setMsg(String.format("学生编号有误 %s",studentId));
            return false ;
        }
        return true ;
    }
}
