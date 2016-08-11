package com.cn.mogo.sunEdu.core.service;

import com.cn.mogo.sunEdu.core.model.Combine;
import com.cn.mogo.sunEdu.core.model.StuGroup;
import com.cn.mogo.sunEdu.core.model.Student;
import com.cn.mogo.sunEdu.core.model.Teacher;
import com.cn.mogo.sunEdu.core.model.params.GroupBean;
import com.cn.mogo.sunEdu.core.model.params.StuForGroup;
import com.cn.mogo.sunEdu.core.model.params.StudentParam;

import java.util.List;

/**
 * Created by Administrator on 2016/6/14 0014.
 */
public interface StuGroupService {

    //获取班级人数
    int getCount(Integer groupId);

    List<Student> getStuList(Integer groupId);
    //删除班级成员
    int delStudent(StuGroup stuGroupBean);
    //（我的）中的班级消息
   List<Combine> getMyClassMsg(Integer teacherId);
    //确认添加
    int relAdd(StuForGroup stuForGroup);
    //班群消息审核
    int checkApply(Combine combine);
    //（班群）中的班群消息
    List<Combine> getGroupClassMsg(Integer groupId);
    //（学生端)班群列表
    List<GroupBean> getGroupBeanList(StudentParam studentParam);
    //查询班级
    Integer applyAdd(StuGroup stuGroup);
    //获取已加入班级成员
    List<Student> getAddStuIdList(Integer groupId);
}
