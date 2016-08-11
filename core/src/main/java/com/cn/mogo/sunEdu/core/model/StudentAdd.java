package com.cn.mogo.sunEdu.core.model;/**
 * Created by Administrator on 2016/6/15 0015.
 */

import java.util.List;

/**
 * StudentAdd
 *
 * @author xufeng
 * @date 2016/6/15 0015
 */
public class StudentAdd {
    //班群名
    private String groupName;
    //班群Id
    private Integer groupId;
    //学生信息List
    private List<Student> studentList;

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }

    public Integer getGroupId() {
        return groupId;
    }

    public void setGroupId(Integer groupId) {
        this.groupId = groupId;
    }

    public List<Student> getStudentList() {
        return studentList;
    }

    public void setStudentList(List<Student> studentList) {
        this.studentList = studentList;
    }
}
