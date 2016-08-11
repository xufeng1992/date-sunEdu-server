package com.cn.mogo.sunEdu.core.service;

import com.cn.mogo.sunEdu.core.model.Student;
import com.cn.mogo.sunEdu.core.model.Teacher;
import com.cn.mogo.sunEdu.core.model.params.StudentParam;

import java.util.List;

/**
 * Created by Administrator on 2016/6/16 0016.
 */
public interface StudentService {
    //根据学号查询
    List<Student> findStudentById(Integer studentId);
    //根据姓名查询
    List<Student> findStudentByName(String name);
    //根据手机号查询学生
    Integer findExistUserByTelNO(String telNo);
    //创建学生用户
    Integer CreateUserSelective(Student user);
    //根据手机号码查询学生信息
    Student findUserByTelNo(String telNo);
    //更新学生信息
    Integer updateUserById(Student stu);
    //获取学生信息
    Student selectStuById(Integer studentId);
//    //修改学生信息
//    Integer updateStuById(StudentParam studentParam);

    Integer updateUserInfo(StudentParam studentParam);
    //获取学生信息
    List<Student> selectStuByIds(String studentIds);
}
