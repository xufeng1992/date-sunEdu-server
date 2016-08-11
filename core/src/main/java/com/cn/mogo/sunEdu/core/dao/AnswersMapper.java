package com.cn.mogo.sunEdu.core.dao;

import com.cn.mogo.sunEdu.core.model.Answers;
import com.cn.mogo.sunEdu.core.model.params.student.HomeWorkAnswerLookupParams;
import com.cn.mogo.sunEdu.core.model.vo.student.HomeWorkAnswerLookupVo;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface AnswersMapper {
//    int deleteByPrimaryKey(Integer id);
//
//    int insert(Answers record);
//
//    int insertSelective(Answers record);
//
//    Answers selectByPrimaryKey(Integer id);
//
//    int updateByPrimaryKeySelective(Answers record);
//
//    int updateByPrimaryKey(Answers record);

    int uploadImage(Answers answers);

    /**
     * 学生查看老师发布作业的答案
     *
     * @param lookupParams
     * @return
     */
    HomeWorkAnswerLookupVo selectHomeWorkAnswerLookup(HomeWorkAnswerLookupParams lookupParams);
    //获取科目
    List<Integer> getSubjectName(Integer studentId);

    Answers selectAnswerByHomeworkId(Integer homeworkId);
}