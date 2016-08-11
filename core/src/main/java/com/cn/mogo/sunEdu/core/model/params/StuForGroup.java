package com.cn.mogo.sunEdu.core.model.params;/**
 * Created by Administrator on 2016/6/15 0015.
 */

/**
 * StuForGroup
 *
 * @author xufeng
 * @date 2016/6/15 0015
 */
public class StuForGroup {

    private Integer groupId;

    private Integer[] studentId;

    private Byte status;

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public Integer[] getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer[] studentId) {
        this.studentId = studentId;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
