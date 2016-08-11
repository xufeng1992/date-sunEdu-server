package com.cn.mogo.sunEdu.core.service;

import com.cn.mogo.sunEdu.core.model.Group;

import java.util.List;

/**
* Created by Administrator on 2016/6/13 0013.
*/
public interface GroupService {

    //添加班群
    int createGroup(Group groupBean);
    //获取群组列表
    List<Group> getGroupList(Integer teacherId);

    Group getGroupDetails(Integer groupId);

    void updateGroup(int type, Group group);
    //查询班级
    List<Group> stuFindClass(String groupName);
}

