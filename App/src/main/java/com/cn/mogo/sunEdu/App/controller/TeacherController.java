package com.cn.mogo.sunEdu.App.controller;/**
 * Created by Administrator on 2016/6/15 0015.
 */

import com.cn.mogo.sunEdu.App.constant.StatusConstant;
import com.cn.mogo.sunEdu.App.json.BaseJSONRequest;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.App.json.model.StudentBean;
import com.cn.mogo.sunEdu.App.utils.CommUtil;
import com.cn.mogo.sunEdu.core.dao.TeacherMapper;
import com.cn.mogo.sunEdu.core.model.StuGroup;
import com.cn.mogo.sunEdu.core.model.Student;
import com.cn.mogo.sunEdu.core.model.StudentAdd;
import com.cn.mogo.sunEdu.core.model.Teacher;
import com.cn.mogo.sunEdu.core.model.params.StuForGroup;
import com.cn.mogo.sunEdu.core.service.StuGroupService;
import com.cn.mogo.sunEdu.core.service.StudentService;
import com.cn.mogo.sunEdu.core.service.TeacherService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;

/**
 * TeacherController
 *
 * @author xufeng
 * @date 2016/6/15 0015
 */
@RequestMapping(value="/api/app")
@RestController
public class TeacherController {

    @Autowired
    private TeacherService teacherService;
    @Autowired
    private StuGroupService stuGroupService;
    @Autowired
    private StudentService studentService;
    /**
     * 添加学生
     * @param request
     * @return
     */
    @RequestMapping(value="/addStudent", method = RequestMethod.POST)
    public BaseJSONResponse<List<StudentAdd>> addStudent(@RequestBody BaseJSONRequest<Teacher> request) {

        BaseJSONResponse<List<StudentAdd>> response = new BaseJSONResponse<List<StudentAdd>>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token ==StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            Teacher teacher = request.getBusiness();
            List<StudentAdd> gruStuList = teacherService.gruStuList(teacher);
            response.setData(gruStuList);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(StatusConstant.SUCCESS);
            response.setMessage("成功跳转到添加学生页面");
        }
        return response;
    }

    /**
     * 确定添加学生
     * @param request
     * @return
     */
    @RequestMapping(value = "/relAdd", method = RequestMethod.POST)
    public BaseJSONResponse<String> realAdd(@RequestBody BaseJSONRequest<StuForGroup> request) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token ==StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            StuForGroup stuForGroup = request.getBusiness();
            Integer groupId = stuForGroup.getGroupId();
            Integer[] studentId = stuForGroup.getStudentId();
            if(groupId > 0 && (studentId.length)>0 ) {
                int ret = stuGroupService.relAdd(stuForGroup);
                //返回值为0时
                if (ret==0) {
                    response.setMessage("学生已在该群中");
                    response.setSuccess(StatusConstant.FALSE);
                    response.setStatusCode(StatusConstant.RETURN_FAILED);
                } else {
                    response.setMessage("添加学生成功");
                    response.setSuccess(StatusConstant.SUCCESS);
                    response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                }
            } else {
                response.setMessage("请选择要添加的学生");
                response.setSuccess(StatusConstant.FALSE);
                response.setStatusCode(StatusConstant.RETURN_FAILED);
            }
        }
        return response;
    }

    /**
     *查询学生
     * @param request
     * @return
     */
    @RequestMapping(value = "/findStudent", method = RequestMethod.POST)
    public BaseJSONResponse<List<Student>> findStudent(@RequestBody BaseJSONRequest<StudentBean> request) {
        BaseJSONResponse<List<Student>> response = new BaseJSONResponse<List<Student>>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token ==StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            StudentBean studentBean = request.getBusiness();
            String nameOrId = studentBean.getNameOrId();
            List<Student> studentList = new ArrayList<Student>();
            CommUtil commUtil = new CommUtil();
            if(commUtil.isNumeric(nameOrId)){
                Integer studentId = Integer.parseInt(nameOrId,10);
                studentList = studentService.findStudentById(studentId);
            } else {
                studentList = studentService.findStudentByName(nameOrId);
            }
            if(studentList.equals("") || studentList==null) {
                response.setMessage("该学生不存在");
            } else{
                response.setMessage("查询学生成功");
            }
            response.setData(studentList);
            response.setSuccess(StatusConstant.SUCCESS);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
        }
        return response;
    }


}
