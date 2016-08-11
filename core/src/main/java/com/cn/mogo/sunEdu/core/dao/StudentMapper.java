package com.cn.mogo.sunEdu.core.dao;

import com.cn.mogo.sunEdu.core.model.Student;
import com.cn.mogo.sunEdu.core.model.vo.StudentCommitVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface StudentMapper {

    Student selectByStuId(Integer studentId);
    //根据Id模糊查询
    List<Student> findStudentById(Integer studentId);
    //根据name模糊查询
    List<Student> findStudentByName(String name);
    //根据学生编号查询学生信息列表
    List<StudentCommitVo> selectStudentListByIds(List<String> ids);
    //根据手机号查询学生
    Integer findExistUserByTelNO(String telNo);
    //创建学生用户
    Integer createUserSelective(Student user);
    //根据学生号码查询学生信息
    Student findUserByTelNo(String telNo);
    //更新学生信息
    Integer updateByPrimaryKeySelective(Student stu);
    //获取学生信息
    Student selectStuById(Integer studentId);
    //获取学生手机号码
    List<String> selectTelByIds(List<Integer> studentIdList);
    //获取学生信息
    List<Student> selectStuByIds(String studentIds);
}