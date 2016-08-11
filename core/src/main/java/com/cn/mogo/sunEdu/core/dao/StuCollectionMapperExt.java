package com.cn.mogo.sunEdu.core.dao;


import com.cn.mogo.sunEdu.core.model.params.student.StudentCollectionParams;
import com.cn.mogo.sunEdu.core.model.vo.student.StudentCollectionWorkVo;

import java.util.List;

public interface StuCollectionMapperExt extends StuCollectionMapper {

    /**
     * 获取学生收藏列表
     *
     * @param params
     * @return
     */
    List<StudentCollectionWorkVo> selectStudentCollectionList(StudentCollectionParams params);

    /**
     * 获取学生收藏作业详细信息
     *
     * @param params
     * @return
     */
    List<StudentCollectionWorkVo> selectStudentCollectionDetailList(StudentCollectionParams params);

}