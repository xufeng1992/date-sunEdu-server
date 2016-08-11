package com.cn.mogo.sunEdu.App.controller;/**
 * Created by Administrator on 2016/6/15 0015.
 */

import com.cn.mogo.sunEdu.App.constant.StatusConstant;
import com.cn.mogo.sunEdu.App.json.BaseJSONRequest;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;
import com.cn.mogo.sunEdu.App.json.model.UserBean;
import com.cn.mogo.sunEdu.App.utils.PhoneUtil;
import com.cn.mogo.sunEdu.App.utils.TokenProcessor;
import com.cn.mogo.sunEdu.App.utils.YZMBean;
import com.cn.mogo.sunEdu.core.common.BCrypt;
import com.cn.mogo.sunEdu.core.common.SMS;
import com.cn.mogo.sunEdu.core.model.Student;
import com.cn.mogo.sunEdu.core.model.params.StudentParam;
import com.cn.mogo.sunEdu.core.service.StudentService;
import com.fasterxml.jackson.core.JsonParseException;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.JsonMappingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

/**
 * StudentController
 *
 * @author xufeng
 * @date 2016/6/15 0015
 */
@RestController
@RequestMapping(value="api/app")
public class StudentController {

    @Autowired
    private RedisTemplate<String,String> redisTemplate;
    @Autowired
    private StudentService studentService;

    @RequestMapping(value="/stuRegister",method = RequestMethod.POST)
    public BaseJSONResponse<String> stuRegister(@RequestBody BaseJSONRequest<StudentParam> request) {

        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        StudentParam studentParam = request.getBusiness();
        //验证码
        String captcha = studentParam.getCaptcha();
        //手机号码
        String telNo = studentParam.getTelNo();
        //密码
        String password = studentParam.getPassword();
        //redis上获取验证码
        String smsCode = redisTemplate.opsForValue().get(telNo);
        if (smsCode == null || !captcha.equals(smsCode)) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(false);
            response.setMessage("短信校验码匹配错误，请重新获取");
        } else if(telNo == null ||!PhoneUtil.phoneNumber(telNo)) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(false);
            response.setMessage("手机号码无效，请重新输入");
        } else if(password == null || password.length()<6 || password.length()>18) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(false);
            response.setMessage("请重新输入6-18位密码");
        }else {
            Integer resnum = studentService.findExistUserByTelNO(telNo);
            if (resnum > 0) {
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setSuccess(false);
                response.setMessage("该手机号码已注册");
            } else {
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                Student user = new Student(telNo,hashed );
                int res = studentService.CreateUserSelective(user);
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                response.setSuccess(true);
                response.setMessage("用户注册成功");
            }
        }
        return response;
    }

    /**
     * 学生登入
     * @param request
     * @return
     */
    @RequestMapping(value = "/stuLogin", method = RequestMethod.POST)
    public BaseJSONResponse<StudentParam> login(@RequestBody BaseJSONRequest<StudentParam> request) {
        BaseJSONResponse<StudentParam> response = new BaseJSONResponse<StudentParam>();
        ObjectMapper objectMapper = new ObjectMapper();
        StudentParam studentParam = request.getBusiness();
        String telNo = studentParam.getTelNo();
        String password = studentParam.getPassword();
        String deviceId = studentParam.getDeviceNumber();

        Student student = studentService.findUserByTelNo(telNo);
        if (student == null) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(false);
            response.setMessage("该用户不存在");
            response.setData(null);
            return response;
        }
        if (student != null && BCrypt.checkpw(password, student.getPassword())) {
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(true);
            response.setMessage("用戶登录成功");
            String jsonUser;
            try {
                jsonUser = objectMapper.writeValueAsString(student);
                String token = TokenProcessor.getInstance()
                        .generateToken(student.getStudentId() + "+" + student.getTelNo() + "+" + deviceId, true);
                redisTemplate.opsForValue().set(token, jsonUser, 15, TimeUnit.DAYS);
                response.setData(new StudentParam(student.getStudentId(),student.getName(),student.getTelNo(),token,student.getEmail()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
//                logger.error(e.getMessage());
            }
        } else {
            response.setSuccess(false);
            response.setMessage("用戶登录失败，用户名或密码错误");
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setData(null);
        }

        return response;

    }

    /**
     * 忘记密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/stuFindPWD", method = RequestMethod.POST)
    public BaseJSONResponse<String> findPWD(@RequestBody BaseJSONRequest<StudentParam> request) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        StudentParam studentParam = request.getBusiness();
        //验证码
        String captcha = studentParam.getCaptcha();
        //手机号
        String telNo = studentParam.getTelNo();
        //新密码
        String newPass = studentParam.getPassword();
        //确认密码
        String reenterPassword = studentParam.getReenterPassword();

        String smsCode = redisTemplate.opsForValue().get(telNo);
        if(!newPass.equals(reenterPassword)) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(false);
            response.setMessage("两次输入的密码不一致，请重新输入");
        }//验证码的判定
        else if (smsCode == null || !captcha.equals(smsCode)) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(false);
            response.setMessage("短信校验码不匹配，请重新获取");
        } else {
            Student student = studentService.findUserByTelNo(telNo);
            if (student != null) {
                String hashed = BCrypt.hashpw(newPass, BCrypt.gensalt());
               Student stu = new Student();
                stu.setStudentId(student.getStudentId());
                stu.setPassword(hashed);
               Integer result =  studentService.updateUserById(stu);
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                response.setSuccess(true);
                response.setMessage("密码已更新");
            } else {
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setSuccess(false);
                response.setMessage("该用户不存在");
            }
        }
        return response;
    }

    /**
     * 获取验证码
     * @param request
     * @return
     * @throws java.io.IOException
     */
    @RequestMapping(value="/stuYzm",method=RequestMethod.POST)
    public BaseJSONResponse<String> yzm(@RequestBody BaseJSONRequest<UserBean> request) throws IOException {

        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        UserBean userBean = new UserBean();
        userBean = request.getBusiness();
        String telNo = userBean.getTelNo();
        if(!PhoneUtil.phoneNumber(telNo)) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setData(null);
            response.setMessage("手机号码格式不正确");
            response.setSuccess(false);

        } else {
            String type = userBean.getType();
            Integer count = studentService.findExistUserByTelNO(userBean.getTelNo());
            //注册时
            if(type.equals("0")) {
                if(count > 0){
                    response.setData(null);
                    response.setSuccess(StatusConstant.FALSE);
                    response.setStatusCode(StatusConstant.RETURN_FAILED);
                    response.setMessage("该手机已经注册");
                } else{
                    //调取发送验证码接口
                    response = yzmProduct(userBean.getTelNo());
                }
                //修改密码时
            } else {
                if(count > 0) {
                    //调取发送验证码接口
                    response = yzmProduct(userBean.getTelNo());
                } else {
                    response.setData(null);
                    response.setMessage("该手机号未注册");
                    response.setStatusCode(StatusConstant.RETURN_FAILED);
                }
            }

        }
        return  response;
    }

    /**
     * 学生的我的个人信息
     * @param request
     * @return
     */
    @RequestMapping(value="/stuInfo",method = RequestMethod.POST)
    public BaseJSONResponse<Student> stuInfo(@RequestBody BaseJSONRequest<Student> request){
        BaseJSONResponse<Student> response = new BaseJSONResponse<Student>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            Student student = request.getBusiness();
            Integer studentId = student.getStudentId();
            //获取学生信息
            Student stu = studentService.selectStuById(studentId);
            response.setData(stu);
            response.setMessage("获取个人信息成功");
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(StatusConstant.SUCCESS);
        }
        return response;
    }

    /**
     * 修改学生信息
     * @param request
     * @return
     */
    @RequestMapping(value = "updateStu",method = RequestMethod.POST)
    public BaseJSONResponse<String> updateStu(@RequestBody BaseJSONRequest<StudentParam> request) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        if(token == StatusConstant.STRING_ENPTY || token == StatusConstant.STRING_NULL) {
            response.setMessage(StatusConstant.TOKEN_ERR_MSG);
            response.setData(null);
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(StatusConstant.FALSE);
        } else {
            StudentParam studentParam = request.getBusiness();
            Integer result = studentService.updateUserInfo(studentParam);
            if(result==1) {
                response.setMessage("个人信息修改成功");
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                response.setSuccess(StatusConstant.SUCCESS);
            } else {
                response.setMessage("个人信息修改失败");
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setSuccess(StatusConstant.FALSE);
            }

        }
        return response;
    }
    //生成验证码
    public BaseJSONResponse<String> yzmProduct(String telNumber) throws HttpException, IOException {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();

        StringBuffer result = new StringBuffer();
        int a = (int) ((Math.random() * 9 + 1) * 100000);
        String ycm = String.valueOf(a);

        if (ycm == null || ycm.length() != 6) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setMessage("验证码无效");
            response.setSuccess(false);
        } else {
            result.append("【杭州磨古科技】 欢迎使用“阳光学习”，您的验证码： " + ycm + "十分钟内有效。");
            try {
                if (SMS.sendMessage(telNumber, result.toString())) {
                    yzmToken(telNumber, ycm);
                    response.setMessage("验证码发送成功");
                    response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                    response.setSuccess(true);
                }
            } catch (IOException e) {
                response.setMessage("验证码发送异常");
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setSuccess(false);
            }
        }
        return response;

    }
    // 验证码 写入缓存
    public void yzmToken(String telNumber, String code) {
        YZMBean yzm = new YZMBean();
        yzm.setTimeout(60 * 10);// 10分钟
        yzm.setTelNumber(telNumber);
        yzm.setCode(code);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(yzm.getTelNumber(), yzm.getCode(), yzm.getTimeout(), TimeUnit.SECONDS);
    }
    /**
     * 取得本地数据
     * @param token
     * @return
     * @throws com.fasterxml.jackson.core.JsonParseException
     * @throws com.fasterxml.jackson.databind.JsonMappingException
     * @throws IOException
     */
    public Student getUserBytoken(String token) throws JsonParseException, JsonMappingException, IOException {
        Student student = null;
        if (token != null) {
            ObjectMapper objectMapper = new ObjectMapper();
            String userJSON = redisTemplate.opsForValue().get(token);
            if (userJSON != null && !StatusConstant.STRING_ENPTY.equals(userJSON)) {
                student = objectMapper.readValue(userJSON, Student.class);
            }
        }
        return student;
    }
}
