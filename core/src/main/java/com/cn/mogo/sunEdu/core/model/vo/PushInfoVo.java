package com.cn.mogo.sunEdu.core.model.vo;/**
 * Created by Administrator on 2016/7/12 0012.
 */

import org.springframework.stereotype.Repository;

import java.io.Serializable;

/**
 * PushInfo
 *
 * @author xufeng
 * @date 2016/7/12 0012
 */
@Repository
public class PushInfoVo implements Serializable {

    //老师姓名
    private String name;
    //老师Id
    private Integer teacherId;
    //班级名称
    private String groupName;

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getGroupName() {
        return groupName;
    }

    public void setGroupName(String groupName) {
        this.groupName = groupName;
    }
}
