package com.cn.mogo.sunEdu.App.controller;

import com.cn.mogo.sunEdu.App.constant.StatusConstant;
import com.cn.mogo.sunEdu.App.json.BaseJSONRequest;
import com.cn.mogo.sunEdu.App.json.model.UserBean;
import com.cn.mogo.sunEdu.App.utils.PhoneUtil;
import com.cn.mogo.sunEdu.App.utils.TokenProcessor;
import com.cn.mogo.sunEdu.App.utils.YZMBean;
import com.cn.mogo.sunEdu.core.common.BCrypt;
import com.cn.mogo.sunEdu.core.common.SMS;
import com.cn.mogo.sunEdu.core.model.Teacher;
import com.cn.mogo.sunEdu.core.service.TeacherService;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.apache.commons.httpclient.HttpException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.data.redis.core.ValueOperations;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import com.cn.mogo.sunEdu.App.json.BaseJSONResponse;

import java.io.IOException;
import java.lang.reflect.Method;
import java.util.concurrent.TimeUnit;

/**
 * Created by xufeng on 2016/6/3.
 */
@RequestMapping(value = "/api/app")
@RestController
public class LoginController {

    protected static Logger logger = LoggerFactory.getLogger(LoginController.class);
    @Autowired
    protected RedisTemplate<String, String> redisTemplate;
    @Autowired
    private TeacherService teacherService;

    /**
     * 教师注册
     * @param request
     * @return
     */
    @RequestMapping(value = "/register", method = RequestMethod.POST)
    public BaseJSONResponse<String> register(@RequestBody BaseJSONRequest<UserBean> request) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();

        UserBean userBean = request.getBusiness();
        //密码
        String password = userBean.getPassword();
        //手机号码
        String telNo = userBean.getTelNo();
        //短信验证码
        String captcha = userBean.getCaptcha();
        String smsCode = redisTemplate.opsForValue().get(telNo);
        if (smsCode == null || !captcha.equals(smsCode)) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(false);
            response.setMessage("短信校验码匹配错误，请重新获取");
        } else {
            int resnum = teacherService.findExistUserByTelNO(telNo);
            if (resnum > 0) {
                response.setStatusCode(StatusConstant.RETURN_FAILED);
                response.setSuccess(false);
                response.setMessage("该手机号码已注册");
            } else {
                String hashed = BCrypt.hashpw(password, BCrypt.gensalt());
                Teacher user = new Teacher(hashed, telNo);
                int res = teacherService.CreateUserSelective(user);
                response.setStatusCode(StatusConstant.RETURN_SUCCESS);
                response.setSuccess(true);
                response.setMessage("用户 " + res + " 注册成功");
            }
        }
        return response;
    }

    /**
     * 教师登入
     * @param request
     * @return
     */
    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public BaseJSONResponse<UserBean> login(@RequestBody BaseJSONRequest<UserBean> request) {
        BaseJSONResponse<UserBean> response = new BaseJSONResponse<UserBean>();
        ObjectMapper objectMapper = new ObjectMapper();
        UserBean userBean = request.getBusiness();
        String telNo = userBean.getTelNo();
        String password = userBean.getPassword();
        String deviceId = userBean.getDeviceNumber();

        Teacher teacher = new Teacher();
        teacher = teacherService.findUserByTelNo(telNo);
        if (teacher == null) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(false);
            response.setMessage("该用户不存在");
            response.setData(null);
            return response;
        }
        if (teacher != null && BCrypt.checkpw(password, teacher.getPassword())) {
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(true);
            response.setMessage("用戶登录成功");
            String jsonUser;
            try {
                jsonUser = objectMapper.writeValueAsString(teacher);
                String token = TokenProcessor.getInstance()
                        .generateToken(teacher.getTeacherId() + "+" + teacher.getTelNo() + "+" + deviceId, true);
                redisTemplate.opsForValue().set(token, jsonUser, 15, TimeUnit.DAYS);
                response.setData(new UserBean(teacher.getTeacherId(), token,teacher.getSubject(),teacher.getName()));
            } catch (JsonProcessingException e) {
                e.printStackTrace();
                logger.error(e.getMessage());
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
     * 注销
     * @param request
     * @return
     */
    @RequestMapping(value = "/logout", method = RequestMethod.POST)
    public BaseJSONResponse<String> logout(@RequestBody BaseJSONRequest<UserBean> request) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        String token = request.getToken();
        if (token == null || "".equals(token)) {
            response.setStatusCode(StatusConstant.RETURN_TOKEN_ERROR);
            response.setSuccess(false);
            response.setMessage("TOKEN异常，请重新登录");
            response.setData(null);
        } else {
            redisTemplate.delete(token);
            response.setStatusCode(StatusConstant.RETURN_SUCCESS);
            response.setSuccess(true);
            response.setMessage("用戶注销成功");
            response.setData(null);
        }
        return response;
    }

    /**
     * 找回密码
     * @param request
     * @return
     */
    @RequestMapping(value = "/findPWD", method = RequestMethod.POST)
    public BaseJSONResponse<String> findPWD(@RequestBody BaseJSONRequest<UserBean> request) {
        BaseJSONResponse<String> response = new BaseJSONResponse<String>();
        UserBean userBean = request.getBusiness();
        String captcha = userBean.getCaptcha();
        String telNO = userBean.getTelNo();
        String newPass = userBean.getNewPassword();
        String hashed = BCrypt.hashpw(newPass, BCrypt.gensalt());
        String smsCode = redisTemplate.opsForValue().get(telNO);
        if (smsCode == null || !captcha.equals(smsCode)) {
            response.setStatusCode(StatusConstant.RETURN_FAILED);
            response.setSuccess(false);
            response.setMessage("短信校验码不匹配，请重新获取");
        } else {
            Teacher teacher = teacherService.findUserByTelNo(telNO);
            if (teacher != null) {
                teacher = new Teacher();
                teacher.setTelNo(telNO);
                teacher.setPassword(hashed);
                teacherService.updateUserByTelNo(teacher);
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
     * @throws IOException
     */
    @RequestMapping(value="/yzm",method=RequestMethod.POST)
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
            int count = teacherService.findExistUserByTelNO(userBean.getTelNo());
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

    public void yzmToken(String telNumber, String code) {
        YZMBean yzm = new YZMBean();
        yzm.setTimeout(60 * 10);// 10分钟
        yzm.setTelNumber(telNumber);
        yzm.setCode(code);
        ValueOperations<String, String> ops = redisTemplate.opsForValue();
        ops.set(yzm.getTelNumber(), yzm.getCode(), yzm.getTimeout(), TimeUnit.SECONDS);
    }// 验证码 写入缓存
}
