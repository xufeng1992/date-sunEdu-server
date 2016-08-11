package com.cn.mogo.sunEdu.App.json.model;/**
 * Created by Administrator on 2016/6/7 0007.
 */


import com.fasterxml.jackson.annotation.JsonInclude;

import java.util.Date;

/**
 * UserBean
 *
 * @author xufeng
 * @date 2016/6/7 0007
 */
@JsonInclude(JsonInclude.Include.NON_NULL)
public class UserBean {
    private Integer teacherId;

    private String telNo;

    private String password;

    private String deviceNumber;

    private Integer deviceType;

    private Integer subject;

    private String subjectName;

    private String name;

    private String token;

    private  String newPassword;
    //验证码
    private String captcha;

    //注册“0”，找回密码“1”
    private String type;
    //头像URL
    private String avatar;

    public UserBean(Integer teacherId,Integer subject,String subjectName,String name){
        this.teacherId = teacherId;
        this.subject = subject;
        this.subjectName = subjectName;
        this.name = name;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }
    public String getNewPassword() {
        return newPassword;
    }

    public void setNewPassword(String newPassword) {
        this.newPassword = newPassword;
    }

    public UserBean(Integer teacherId, String token, Integer subject, String name) {
        super();
        this.subject = subject;
        this.name = name;
        this.teacherId = teacherId;
        this.token = token;
    }
    public UserBean() {
    }

    public UserBean(String telNo,String password,String captcha) {
//        super();
        this.telNo = telNo;
        this.password = password;
        this.captcha = captcha;

    }
    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    public String getCaptcha() {
        return captcha;
    }

    public void setCaptcha(String captcha) {
        this.captcha = captcha;
    }
    public Integer getTeacherId() {
        return teacherId;
    }

    public void setTeacherId(Integer teacherId) {
        this.teacherId = teacherId;
    }

    public String getTelNo() {
        return telNo;
    }

    public void setTelNo(String telNo) {
        this.telNo = telNo == null ? null : telNo.trim();
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password == null ? null : password.trim();
    }

    public String getDeviceNumber() {
        return deviceNumber;
    }

    public void setDeviceNumber(String deviceNumber) {
        this.deviceNumber = deviceNumber == null ? null : deviceNumber.trim();
    }
    public Integer getDeviceType() {
        return deviceType;
    }

    public void setDeviceType(Integer deviceType) {
        this.deviceType = deviceType;
    }

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name == null ? null : name.trim();
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }
}
