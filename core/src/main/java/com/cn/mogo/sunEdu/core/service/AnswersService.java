package com.cn.mogo.sunEdu.core.service;

import com.cn.mogo.sunEdu.core.model.Answers;

import java.util.List;

/**
 * Created by Administrator on 2016/6/21 0021.
 */
public interface AnswersService {
    int uploadImage(Answers answers);
    //科目
    List<Integer> getSubjectName(Integer studentId);
}
