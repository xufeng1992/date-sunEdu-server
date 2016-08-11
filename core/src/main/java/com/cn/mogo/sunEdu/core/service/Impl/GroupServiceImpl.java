package com.cn.mogo.sunEdu.core.service.Impl;/**
* Created by Administrator on 2016/6/13 0013.
*/

import com.cn.mogo.sunEdu.core.dao.GroupMapper;
import com.cn.mogo.sunEdu.core.model.Group;
import com.cn.mogo.sunEdu.core.service.GroupService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
* GroupServiceImpl
*
* @author xufeng
* @date 2016/6/13 0013
*/
@Service("groupService")
public class GroupServiceImpl implements GroupService {

    @Autowired
    private GroupMapper groupMapper;

    //创建群组
     public int createGroup(Group group) {
        return groupMapper.createGroup(group);
    }

    //群组列表
    public List<Group> getGroupList(Integer teacherId) {
        return groupMapper.groupList(teacherId);
    }

    public Group getGroupDetails(Integer groupId) {
        return groupMapper.getGroupDetails(groupId);
    }

    public void updateGroup(int type, Group group) {
        //type==0时修改简介
        if(type == 0) {
          group.setGroupName(null);
            //type==1时修改群名称
        } else if(type == 1) {
            group.setAnnouncement(null);
        }
        groupMapper.updateGroup(group);

    }
    //学生端查询班级
    public List<Group> stuFindClass(String groupName) {
        return groupMapper.stuFindClass(groupName);
    }

}
