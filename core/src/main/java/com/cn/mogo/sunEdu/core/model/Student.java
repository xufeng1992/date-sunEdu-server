package com.cn.mogo.sunEdu.core.model;

import java.util.Date;

public class Student {
    //学生Id
    private Integer studentId;
    //学生姓名
    private String name;
    //名字首页字母
    private String initials;
    //头像
    private String avatar;
    //年级段
    private String gread;
    //手机号码
    private String telNo;
    //邮箱
    private String email;
    //密码
    private String password;
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

    public Student(){
        super();
    }
    public Student(String telNo,String password){
        this.telNo = telNo;
        this.password = password;
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

    public String getGread() {
        return gread;
    }

    public void setGread(String gread) {
        this.gread = gread;
    }
}