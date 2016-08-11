package com.cn.mogo.sunEdu.core.service.Impl;/**
 * Created by Administrator on 2016/6/16 0016.
 */

import com.cn.mogo.sunEdu.core.common.BCrypt;
import com.cn.mogo.sunEdu.core.dao.StudentMapper;
import com.cn.mogo.sunEdu.core.model.Student;
import com.cn.mogo.sunEdu.core.model.Teacher;
import com.cn.mogo.sunEdu.core.model.params.StudentParam;
import com.cn.mogo.sunEdu.core.service.StudentService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * StudentServiceImpl
 *
 * @author xufeng
 * @date 2016/6/16 0016
 */
@Service
public class StudentServiceImpl implements StudentService {

    @Autowired
    private StudentMapper studentMapper;
    //根据学生Id查询学生
    public List<Student> findStudentById(Integer studentId) {
        return studentMapper.findStudentById(studentId);
    }
    //根据学生姓名查询学生
    public List<Student> findStudentByName(String name) {
        return studentMapper.findStudentByName(name);
    }
    //根据手机号查询学生
    public Integer findExistUserByTelNO(String telNo) {
        return studentMapper.findExistUserByTelNO(telNo);
    }
    //创建学生用户
    public Integer CreateUserSelective(Student user) {
        return studentMapper.createUserSelective(user);
    }
    //根据学生手机号码查询学生信息
    public Student findUserByTelNo(String telNo) {
        return studentMapper.findUserByTelNo(telNo);
    }
    //更新学生信息
    public Integer updateUserById(Student stu) {
        return studentMapper.updateByPrimaryKeySelective(stu);
    }
    //获取学生信息
    public Student selectStuById(Integer studentId) {
        return studentMapper.selectStuById(studentId);
    }
    //修改学生信息
    public Integer updateUserInfo(StudentParam studentParam) {
        //学生Id
        Integer studentId = studentParam.getStudentId();
        //判断符
        Integer flag = studentParam.getFlag();
        //返回结果
        Integer result =0;
        Student stu = new Student();
        //修改密码
        if(flag==1) {
            //数据库中取得学生信息
            Student student = studentMapper.selectByStuId(studentId);
            //新密码
            String newPassword = studentParam.getPassword();
            //旧密码
            String oldPassword = studentParam.getOldPassword();
            //再次确认新密码
            String reenterPassword = studentParam.getPassword();

            //新密码和再次输入密码是否一致，学生信息是否为null,新密码和旧密码是否一致的判断
            if(newPassword.equals(reenterPassword) && student!=null && BCrypt.checkpw(oldPassword, student.getPassword())) {
                //密码加密
                String hashed = BCrypt.hashpw(newPassword, BCrypt.gensalt());
                stu.setStudentId(studentParam.getStudentId());
                stu.setPassword(hashed);
                //更新
                result= studentMapper.updateByPrimaryKeySelective(stu);

            } else{
                result =0;
            }
        } else {
            stu.setStudentId(studentParam.getStudentId());
            //姓名
            stu.setName(studentParam.getName());
            //年级段
            stu.setGread(studentParam.getGread());
            //更新
            result= studentMapper.updateByPrimaryKeySelective(stu);
        }
        return result;
    }

    //获取学生信息
    public List<Student> selectStuByIds(String studentIds) {
        return studentMapper.selectStuByIds(studentIds);
    }


}
