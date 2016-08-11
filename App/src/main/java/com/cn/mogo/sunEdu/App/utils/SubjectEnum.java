package com.cn.mogo.sunEdu.App.utils;

/**
 * Created by Administrator on 2016/6/17 0017.
 */
public enum SubjectEnum {

    //语文
    CHINESE(1,"语文"),
    //数学
    MATH(2,"数学"),
    //科学
    SCIENCE(3,"科学"),
    //英语
    ENGLISH(4,"英语"),
    //美术
    ART(5,"美术");
    //科目标识
    private Integer subject;
    //科目名
    private String subjectName;

    public Integer getSubject() {
        return subject;
    }

    public void setSubject(Integer subject) {
        this.subject = subject;
    }

    public String getSubjectName() {
        return subjectName;
    }

    public void setSubjectName(String subjectName) {
        this.subjectName = subjectName;
    }

    private SubjectEnum(Integer subjectId, String name) {
        subject = subjectId;
        subjectName = name;
    }
    /**
     * 枚举获取课程名
     * @param subjectId
     * @return
     */
    public static SubjectEnum getSubjecName(Integer subjectId){
        SubjectEnum[] allSubject =  SubjectEnum.values();
        for (SubjectEnum subject : allSubject){
            if(subject.getSubject()== subjectId) {
                try {
                    return subject;
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }

        }
        return null;
    }


}
