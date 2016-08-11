package com.cn.mogo.sunEdu.core.model;/**
 * Created by Administrator on 2016/6/15 0015.
 */

/**
 * Combine
 *
 * @author xufeng
 * @date 2016/6/15 0015
 */
public class Combine {
    //学生Id
    private Integer studentId;
    //学生姓名
    private String name;
    //班群Id
    private Integer groupId;
    //班群名
    private String groupName;
    //状态
    private Byte status;

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }
}
