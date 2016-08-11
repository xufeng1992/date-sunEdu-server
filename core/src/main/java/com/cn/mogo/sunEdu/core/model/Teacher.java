package com.cn.mogo.sunEdu.core.model;

import java.io.Serializable;
import java.util.Date;

public class Teacher implements Serializable{
    //老师Id
    private Integer teacherId;
    //手机号码
    private String telNo;
    //密码
    private String password;
    //头像
    private String avatar;
    //设备号
    private String deviceNumber;
    //登入设备类型
    private Integer deviceType;
    //科目Id
    private Integer subject;
    //姓名
    private String name;
    //添加时间
    private Date addTime;
    //密码尝试错误
    private Integer passwordAttempts;
    //上次登入时间
    private Date lastDate;
    //状态
    private Byte status;

    public Teacher(String password,String telNo){
        super();
        this.password = password;
        this.telNo = telNo;
    }
    public Teacher(){
        super();
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

    public Date getAddTime() {
        return addTime;
    }

    public void setAddTime(Date addTime) {
        this.addTime = addTime;
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

    public Byte getStatus() {
        return status;
    }

    public void setStatus(Byte status) {
        this.status = status;
    }

    public String getAvatar() {
        return avatar;
    }

    public void setAvatar(String avatar) {
        this.avatar = avatar;
    }
}