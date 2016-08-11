package com.cn.mogo.sunEdu.core.dao;

import com.cn.mogo.sunEdu.core.model.Combine;
import com.cn.mogo.sunEdu.core.model.Group;
import com.cn.mogo.sunEdu.core.model.StuGroup;
import com.cn.mogo.sunEdu.core.model.Student;
import com.cn.mogo.sunEdu.core.model.params.GroupBean;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Map;

@Repository
public interface StuGroupMapper {

    //班级人数
    int getCount(int groupId);
    /*获取已经加入群的学生Ids*/
    List<Integer> getStuIdList(Integer groupId);
    /*获取审核加入群的学生Ids*/
    List<Integer> getUpStuIdList(Integer groupId);

    int updateStudent(StuGroup stuGroup);

    List<Combine> getMyClassMsg(Integer teacherId);

    List<StuGroup> getStuGroupList(Integer groupId);

    int relAdd(List<StuGroup> stuGroupList);

    int checkApply(Combine combine);

    List<Combine> getGroupClassMsg(Integer groupId);
    //学生端班群列表
    List<Group> getGroupList(Integer studentId);
    //判断是否已经申请或者已经添加
    Integer selectByStuGroup(StuGroup stuGroup);
    //学生申请添加群
    Integer stuApplyAdd(StuGroup stuGroup);
    //更新学生申请状态
    int relAddUpdate(Map<String, Object> params);
    //班群成员
    List<Integer> getAddStuIdList(Integer groupId);
}