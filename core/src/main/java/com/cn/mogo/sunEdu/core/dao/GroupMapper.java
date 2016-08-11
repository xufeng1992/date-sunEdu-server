package com.cn.mogo.sunEdu.core.dao;

import com.cn.mogo.sunEdu.core.model.Group;
import com.cn.mogo.sunEdu.core.model.vo.PushInfoVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface GroupMapper {

    //创建群
    int createGroup(Group record);
    //群组列表
    List<Group> groupList(Integer teacherId);

    Group getGroupDetails(Integer groupId);

    int deleteByPrimaryKey(Integer groupId);

    //修改群消息
    void updateGroup(Group group);
    //查询班级列表
    List<Group> stuFindClass(String groupName);
    //获取老师名和班级名
    PushInfoVo getPushInfo(Integer groupId);
}