package com.cn.mogo.sunEdu.core.model.params;/**
 * Created by Administrator on 2016/7/4 0004.
 */

import com.fasterxml.jackson.annotation.JsonInclude;

import java.io.Serializable;
import java.util.Date;

/**
 * StudentParam
 *
 * @author xufeng
 * @date 2016/7/4 0004
 */
//@JsonInclude(JsonInclude.Include.NON_NULL)
public class StudentParam {

    //学生Id
    private Integer studentId;
    //学生姓名
    private String name;
    //名字首页字母
    private String initials;
    //年级段
    private String gread;
    //头像
    private String avatar;
    //手机号码
    private String telNo;
    //邮箱
    private String email;
    //密码
    private String password;
    //旧密码
    private String oldPassword;
    //再次输入密码
    private String reenterPassword;
    //性别
    private Integer sex;
    //设备号
    private String deviceNumber;
    //密码尝试次数
    private Integer passwordAttempts;
    //上次登入时间
    private Date lastDate;
    //注册时间
    private Date registerTime;
    //状态
    private Byte status;
    //验证码
    private String captcha;
    //注册“0”，找回密码“1”
    private String type;
    //toke
    private String token;
    //判断符
    private Integer flag;

    public StudentParam() {
        super();
    }
    public StudentParam(Integer studentId,String name,String telNo,String token,String email) {
        this.studentId = studentId;
        this.name = name;
        this.telNo = telNo;
        this.token = token;
        this.email = email;
    }

    public Integer getStudentId() {
        return studentId;
    }

    public void setStudentId(Integer studentId) {
        this.studentId = studentId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getInitials() {
        return initials;
    }

    public void setInitials(String initials) {
        this.initials = initials == null ? null : initials.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar == null ? null : avatar.trim();
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo == null ? null : telNo.trim();
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email == null ? null : email.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public Integer getSex() {
        return sex;
    }

    public void setSex(Integer sex) {
        this.sex = sex;
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber == null ? null : deviceNumber.trim();
    }

    public Integer getPasswordAttempts() {
        return passwordAttempts;
    }

    public void setPasswordAttempts(Integer passwordAttempts) {
        this.passwordAttempts = passwordAttempts;
    }

    public Date getLastDate() {
        return lastDate;
    }

    public void setLastDate(Date lastDate) {
        this.lastDate = lastDate;
    }

    public Date getRegisterTime() {
        return registerTime;
    }

    public void setRegisterTime(Date registerTime) {
        this.registerTime = registerTime;
    }

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getReenterPassword() {
        return reenterPassword;
    }

    public void setReenterPassword(String reenterPassword) {
        this.reenterPassword = reenterPassword;
    }

    public Integer getFlag() {
        return flag;
    }

    public void setFlag(Integer flag) {
        this.flag = flag;
    }

    public String getOldPassword() {
        return oldPassword;
    }

    public void setOldPassword(String oldPassword) {
        this.oldPassword = oldPassword;
    }

    public String getGread() {
        return gread;
    }

    public void setGread(String gread) {
        this.gread = gread;
    }
}
