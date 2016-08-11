package com.cn.mogo.sunEdu.core.dao;

import com.cn.mogo.sunEdu.core.model.StudentAdd;
import com.cn.mogo.sunEdu.core.model.Teacher;
import org.springframework.stereotype.Repository;

@Repository
public interface TeacherMapper {
    //查询
    Integer selectByMobile(String telNo);
    //添加
    int insertSelective(Teacher record);

    Teacher findUserByTelNo(String telNO);

    void updateByPrimaryKeyTeNO(Teacher teacher);

    Teacher findUserById(Integer teacherId);

    StudentAdd selectByGroupId(Integer groupId);

    int updateSubject(Teacher teacher);
}