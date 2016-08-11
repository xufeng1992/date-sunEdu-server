package com.cn.mogo.sunEdu.core.service.Impl;/**
 * Created by Administrator on 2016/6/21 0021.
 */

import com.cn.mogo.sunEdu.core.dao.AnswersMapper;
import com.cn.mogo.sunEdu.core.model.Answers;
import com.cn.mogo.sunEdu.core.service.AnswersService;
import org.apache.commons.lang3.ObjectUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * AnswersServiceImpl
 *
 * @author xufeng
 * @date 2016/6/21 0021
 */
@Service
public class AnswersServiceImpl implements AnswersService {

    @Autowired
    private AnswersMapper answersMapper;

    //保存答案信息
    public int uploadImage(Answers answers) {
        return answersMapper.uploadImage(answers);
    }

    //获取科目IdList
    public List<Integer> getSubjectName(Integer studentId) {

        List<Integer> subjectIdList = answersMapper.getSubjectName(studentId);

        return subjectIdList;
    }
}
